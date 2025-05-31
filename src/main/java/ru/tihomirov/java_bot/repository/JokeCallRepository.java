package ru.tihomirov.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tihomirov.java_bot.model.JokeCall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JokeCallRepository extends JpaRepository<JokeCall, Long> {

    List<JokeCall> findByUserId(Long userId);

    List<JokeCall> findByJokeId(Long jokeId);

    @Query("SELECT jc.jokeId, COUNT(jc) as cnt FROM joke_call jc GROUP BY jc.jokeId ORDER BY cnt DESC")
    List<Object[]> findTopJokeIds(Pageable pageable);

}
