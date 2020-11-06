package ru.mirea.budget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.budget.dao.UserDao;
import ru.mirea.budget.entity.User;
import ru.mirea.budget.exception.EntityAlreadyExistsException;
import ru.mirea.budget.exception.EntityNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @Override
    public User register(User user) {
        Optional<User> userOptional = userDao.findByLogin(user.getLogin());
        if(userOptional.isPresent()) {
            throw  new EntityAlreadyExistsException("User");
        }
        return userDao.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByLogin(username).orElseThrow(() -> new EntityNotFoundException("User", username));
    }
}
