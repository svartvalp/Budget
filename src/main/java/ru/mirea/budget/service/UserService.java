package ru.mirea.budget.service;

import ru.mirea.budget.entity.User;

public interface UserService {
    User register(User user);
    User getByUsername(String username);
}
