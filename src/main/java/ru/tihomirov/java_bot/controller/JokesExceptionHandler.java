package ru.tihomirov.java_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tihomirov.java_bot.exceptions.ExceptionRespone;
import ru.tihomirov.java_bot.exceptions.JokesNotFoundException;


@ControllerAdvice
public class JokesExceptionHandler {

    @ExceptionHandler(JokesNotFoundException.class)
    public ResponseEntity<ExceptionRespone> handleJokesNotFound (JokesNotFoundException exception) {

        System.out.println("Joke not found with ID: " + exception.getId());
        return ResponseEntity.notFound().build();
    }
}
