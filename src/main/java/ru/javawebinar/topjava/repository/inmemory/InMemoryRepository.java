package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

public class InMemoryRepository implements MealRepository {
    private static final Map<Integer, Meal> map = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    {
        MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        return map.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return map.getOrDefault(id, null);
    }

    @Override
    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    @Override
    public Collection<Meal> getAll() {
        return map.values().isEmpty() ? Collections.EMPTY_LIST : map.values();
    }
}
