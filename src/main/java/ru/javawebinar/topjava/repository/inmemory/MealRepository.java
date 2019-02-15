package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {

    Meal save(Meal meal);

    Meal get(int id);

    boolean delete(int id);

    Collection<Meal> getAll();
}
