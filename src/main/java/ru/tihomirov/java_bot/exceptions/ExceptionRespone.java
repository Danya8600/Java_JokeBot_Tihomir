package ru.tihomirov.java_bot.exceptions;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors
public class ExceptionRespone {

    private Long code;
    private String message;

}
