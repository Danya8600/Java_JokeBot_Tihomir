package ru.tihomirov.java_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tihomirov.java_bot.model.Jokes;

public interface JokesRepository extends CrudRepository<Jokes, Long> {
}