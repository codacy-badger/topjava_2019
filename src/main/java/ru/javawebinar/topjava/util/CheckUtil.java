package ru.javawebinar.topjava.util;

public class CheckUtil {

    public static <T> T check(T t) {
        try {
            if (t == null) {
                throw new NullPointerException();
            } else {
                return t;
            }
        } catch (NullPointerException e) {
            System.out.println("Error");
        }
        return null;
    }

    public static void checkWithId(boolean delete) {
        if (!delete) {
            check(null);
        }
    }
}
