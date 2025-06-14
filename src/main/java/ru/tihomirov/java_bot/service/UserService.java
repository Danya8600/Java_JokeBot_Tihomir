package ru.tihomirov.java_bot.service;

import ru.tihomirov.java_bot.model.User;
import ru.tihomirov.java_bot.model.dto.RegisterRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id); // ➕ Добавлено
    void save(User user);
    void registerUser(RegisterRequest request);
    void changeUserRole(Long userId, String newRoleName); // ➕ Добавлено
}
