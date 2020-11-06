package ru.mirea.budget.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.mirea.budget.entity.User;

import java.util.Optional;

public interface UserDao extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
}
