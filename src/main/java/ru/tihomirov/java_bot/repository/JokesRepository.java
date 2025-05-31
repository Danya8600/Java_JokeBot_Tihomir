package ru.tihomirov.java_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tihomirov.java_bot.model.Jokes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface JokesRepository extends CrudRepository<Jokes, Long>, PagingAndSortingRepository<Jokes, Long> {

    Page<Jokes> findByTitle(String title, Pageable pageable);

}