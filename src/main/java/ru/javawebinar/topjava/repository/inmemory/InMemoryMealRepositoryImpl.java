package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UserUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    private static final Comparator<Meal> SORT_BY_DATETIME_DESC = Comparator.comparing(Meal::getDateTime).reversed();
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.USER_MEALS.forEach(m -> save(UserUtil.USER.getId(), m));
        MealsUtil.ADMIN_MEALS.forEach(m -> save(UserUtil.ADMIN.getId(), m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.info("save meal {} with userId {}", meal, userId);
        repository.putIfAbsent(userId, new HashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            getUserMealMap(userId).put(meal.getId(), meal);
            return meal;
        }
        return getUserMealMap(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        log.info("delete meal with id {} and userId {}", id, userId);
        return getUserMealMap(userId) != null && getUserMealMap(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        log.info("get meal with id {} and userId {}", id, userId);
        return getUserMealMap(userId) != null ? getUserMealMap(userId).getOrDefault(id, null) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll meal with userId {}", userId);
        return getUserMealMap(userId) != null ? getUserMealMap(userId).values().stream().sorted(SORT_BY_DATETIME_DESC).collect(Collectors.toList()) : Collections.EMPTY_LIST;
    }

    @Override
    public List<Meal> getAllBetween(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAllBetween with userId {} between startDat {} and endDate {}", userId, startDate, endDate);
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(),startDate, endDate))
                .sorted(SORT_BY_DATETIME_DESC)
                .collect(Collectors.toList());
    }

    private Map<Integer, Meal> getUserMealMap(int userId) {
        return repository.get(userId);
    }
}

