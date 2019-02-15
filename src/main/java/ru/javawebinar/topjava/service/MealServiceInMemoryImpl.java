package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.inmemory.InMemoryRepository;
import ru.javawebinar.topjava.repository.inmemory.MealRepository;
import ru.javawebinar.topjava.util.CheckUtil;

import java.util.ArrayList;
import java.util.List;

public class MealServiceInMemoryImpl implements MealService {
  private final MealRepository repository = new InMemoryRepository();

  @Override
  public Meal save(Meal meal) {
    return CheckUtil.check(repository.save(meal));
  }

  @Override
  public Meal get(Integer id) {
    return CheckUtil.check(repository.get(id));
  }

  @Override
  public void delete(Integer id) {
    CheckUtil.checkWithId(repository.delete(id));
  }

  @Override
  public List<Meal> getAll() {
    return new ArrayList<>(repository.getAll());
  }
}
