package ru.tihomirov.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tihomirov.java_bot.model.JokeCall;

import java.util.List;

public interface JokeCallRepository extends JpaRepository<JokeCall, Long> {

    List<JokeCall> findByUserId(Long userId);

    List<JokeCall> findByJokeId(Long jokeId);
}
