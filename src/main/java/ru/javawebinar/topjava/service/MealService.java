package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal create(int userId, Meal meal);

    void delete(int userId, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    void update(int userId, Meal meal);

    List<Meal> getAll(int userId);

    List<Meal> getAllBetween(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    default List<Meal> getAllBetween(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return getAllBetween(userId, LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime));
    }
}