package ru.javawebinar.topjava.util

import ru.javawebinar.topjava.model.UserMeal
import ru.javawebinar.topjava.model.UserMealWithExceed
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.util.*

class UserMealsUtil {
    fun main(args: Array<String>) {
        val mealList = Arrays.asList(
                UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        )
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)
        //        .toLocalDate();
        //        .toLocalTime();
    }

    fun getFilteredWithExceeded(mealList: List<UserMeal>, startTime: LocalTime, endTime: LocalTime, caloriesPerDay: Int): List<UserMealWithExceed>? {
        // TODO return filtered list with correctly exceeded field
        return null
    }
}