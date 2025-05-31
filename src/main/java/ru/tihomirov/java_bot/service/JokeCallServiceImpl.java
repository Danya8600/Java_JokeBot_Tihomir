package ru.tihomirov.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.tihomirov.java_bot.model.JokeCall;
import ru.tihomirov.java_bot.repository.JokeCallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RequiredArgsConstructor
@Service
public class JokeCallServiceImpl implements JokeCallService {

    private final JokeCallRepository jokeCallRepository;

    @Override
    public JokeCall addJokeCall(JokeCall jokeCall) {
        return jokeCallRepository.save(jokeCall);
    }

    @Override
    public List<JokeCall> getAllJokeCalls() {
        return jokeCallRepository.findAll();
    }

    @Override
    public List<JokeCall> getJokeCallsByUserId(Long userId) {
        return jokeCallRepository.findByUserId(userId);
    }

    @Override
    public List<JokeCall> getJokeCallsByJokeId(Long jokeId) {
        return jokeCallRepository.findByJokeId(jokeId);
    }

    @Override
    public JokeCall saveJokeCall(JokeCall jokeCall) {
        return jokeCallRepository.save(jokeCall);
    }

    @Override
    public List<Long> getTopJokeIds(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Object[]> results = jokeCallRepository.findTopJokeIds(pageable);
        return results.stream()
                .map(r -> (Long) r[0])
                .toList();
    }

}
