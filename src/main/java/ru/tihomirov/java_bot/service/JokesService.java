package ru.tihomirov.java_bot.service;

import ru.tihomirov.java_bot.model.Jokes;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JokesService {


    public Jokes addJokes(Jokes joke);

    public Page<Jokes> getAllJokes(String title, Pageable pageable);


    public Jokes getJokesById(Long id);

    public void editJokes(Long id, Jokes joke);

    public void deleteJokes(Long id);
}
