package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    Meal save(Meal meal);

    Meal get(Integer id);

    void delete(Integer id);

    List<Meal> getAll();
}
