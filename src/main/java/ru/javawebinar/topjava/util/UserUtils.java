package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtils {
    public static final List<User> USERS = Arrays.asList(
    new User(1, "Sergey", "123@mail.ru", "123", Role.USER),
            new User(1, "Vadim", "321@mail.ru", "321", Role.USER)
            );
}
