package ru.tihomirov.java_bot.service;

import ru.tihomirov.java_bot.model.Jokes;
import java.util.List;

public interface JokesService {


    public Jokes addJokes(Jokes joke);

    public List<Jokes> getAllJokes(String title);


    public Jokes getJokesById(Long id);

    public void editJokes(Long id, Jokes joke);

    public void deleteJokes(Long id);
}
