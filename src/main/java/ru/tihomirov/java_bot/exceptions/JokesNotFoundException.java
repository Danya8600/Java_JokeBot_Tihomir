package ru.tihomirov.java_bot.exceptions;

import lombok.Getter;

@Getter
public class JokesNotFoundException extends RuntimeException {

    private final Long id;

    public JokesNotFoundException(Long id) {
        super("Jokes not found: " + id);
        this.id=id;
    }

    public Long getId() {
        return id;
    }
}
