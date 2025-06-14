package ru.tihomirov.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tihomirov.java_bot.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
