package ru.javawebinar.topjava.util

import java.time.LocalTime

class TimeUtil {
    fun isBetween(lt: LocalTime, startTime: LocalTime, endTime: LocalTime): Boolean {
        return lt in startTime..endTime
    }
}