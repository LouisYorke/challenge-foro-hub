package com.challenge.foro_hub.domain.curso;

import jakarta.persistence.*;
import lombok.*;

@Entity (name="Curso")
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String categoria;
}
