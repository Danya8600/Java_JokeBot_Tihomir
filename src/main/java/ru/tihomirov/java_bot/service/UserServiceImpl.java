package ru.tihomirov.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tihomirov.java_bot.model.Role;
import ru.tihomirov.java_bot.model.User;
import ru.tihomirov.java_bot.model.dto.RegisterRequest;
import ru.tihomirov.java_bot.repository.RoleRepository;
import ru.tihomirov.java_bot.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Роль USER не найдена"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(userRole);
        user.setEnabled(true);
        user.setLocked(false);
        user.setExpired(false);

        userRepository.save(user);
    }

    @Override
    public void changeUserRole(Long userId, String newRoleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Role newRole = roleRepository.findByName(newRoleName.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));

        user.setRole(newRole);
        userRepository.save(user);
    }
}
