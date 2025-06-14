package ru.tihomirov.java_bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tihomirov.java_bot.model.User;
import ru.tihomirov.java_bot.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    // Получить пользователя по ID
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userOpt.get());
    }

    // Изменить роль пользователя
    @PutMapping("/user/{id}/role")
    public ResponseEntity<String> changeUserRole(
            @PathVariable Long id,
            @RequestParam String newRole
    ) {
        try {
            userService.changeUserRole(id, newRole);
            return ResponseEntity.ok("Роль успешно обновлена");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
