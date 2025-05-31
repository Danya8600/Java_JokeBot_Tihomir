package ru.tihomirov.java_bot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "joke_call")
@Table(name = "joke_call")
public class JokeCall {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_call_id_seq")
    @SequenceGenerator(sequenceName = "joke_call_id_seq", name = "joke_call_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "joke_id")
    private Long jokeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "call_time")
    private LocalDateTime callTime;


}
