package ru.javawebinar.topjava.model

import java.time.LocalDateTime

class UserMealWithExceed(val dateTime: LocalDateTime, val description: String, val calories: Int, val exceed: Boolean)