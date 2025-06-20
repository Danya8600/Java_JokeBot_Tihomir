package ru.tihomirov.java_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tihomirov.java_bot.model.Jokes;
import ru.tihomirov.java_bot.service.JokesService;
import ru.tihomirov.java_bot.service.JokesServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@RequestMapping("/api/jokes")
@RestController
public class JokesController {

    private final JokesService jokesService;

    @Autowired
    public JokesController(JokesServiceImpl jokesService) {
        this.jokesService = jokesService;
    }

    @PostMapping
    public ResponseEntity<Jokes> addJokes(@RequestBody Jokes joke) {
        Jokes saved = jokesService.addJokes(joke);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Jokes>> getAllJokes(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Jokes> jokesPage = jokesService.getAllJokes(title, pageable);
        List<Jokes> jokesList = jokesPage.getContent();
        return ResponseEntity.ok(jokesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jokes> getJokesById(@PathVariable("id") Long id) {
        Jokes joke = jokesService.getJokesById(id);
        return ResponseEntity.ok(joke);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editJokes(@PathVariable("id") Long id, @RequestBody Jokes joke) {
        jokesService.editJokes(id, joke);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJokes(@PathVariable("id") Long id) {
        jokesService.deleteJokes(id);
        return ResponseEntity.ok().build();
    }
}