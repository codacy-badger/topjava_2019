package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static <T> T parse(T t1, T t2) {
        return t1 == null ? t2 : t1;
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T t, T start, T end) {
        return t.compareTo(start) >= 0 && t.compareTo(end) <= 0;
    }
}
