package ru.tihomirov.java_bot.service;

import ru.tihomirov.java_bot.model.JokeCall;
import java.util.List;

public interface JokeCallService {

    JokeCall addJokeCall(JokeCall jokeCall);

    List<JokeCall> getAllJokeCalls();

    List<JokeCall> getJokeCallsByUserId(Long userId);

    List<JokeCall> getJokeCallsByJokeId(Long jokeId);

    JokeCall saveJokeCall(JokeCall jokeCall);

    List<Long> getTopJokeIds(int limit);

}
