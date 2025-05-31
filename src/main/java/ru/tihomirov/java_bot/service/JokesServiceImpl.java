package ru.tihomirov.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tihomirov.java_bot.exceptions.JokesNotFoundException;
import ru.tihomirov.java_bot.model.Jokes;
import ru.tihomirov.java_bot.repository.JokesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class JokesServiceImpl implements JokesService{

    private final JokesRepository jokesRepository;

    public Jokes addJokes(Jokes joke) {
        return jokesRepository.save(joke);
    }

    public Page<Jokes> getAllJokes(String title, Pageable pageable) {
        if (title != null) {
            return jokesRepository.findByTitle(title, pageable);
        } else {
            return jokesRepository.findAll(pageable);
        }
    }


    public Jokes getJokesById(Long id) {
        Optional<Jokes> jokes = jokesRepository.findById(id);
        if (jokes.isPresent()){
            return jokes.get();
        }
        else {
            throw new JokesNotFoundException(id) ;
        }
    }

    public void editJokes(Long id, Jokes joke) {
        if (!jokesRepository.existsById(id)) {
            throw new JokesNotFoundException(id);
        }
        joke.setId(id);
        jokesRepository.save(joke);
    }

    public void deleteJokes(Long id) {
        if (!jokesRepository.existsById(id)) {
            throw new JokesNotFoundException(id);
        }
        jokesRepository.deleteById(id);
    }
}