package ru.tihomirov.java_bot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_id_seq")
    @SequenceGenerator(name = "authority_id_seq", sequenceName = "authority_id_seq", allocationSize = 1)
    private Long id;

    private String name;
}
