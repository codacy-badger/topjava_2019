package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final User USER = new User(1, "User", "user@mail.ru", "user", Role.ROLE_USER);
    public static final User ADMIN = new User(2, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final List<User> USERS = Arrays.asList(USER, ADMIN);
}