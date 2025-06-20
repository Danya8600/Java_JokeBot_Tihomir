package ru.tihomirov.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tihomirov.java_bot.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
