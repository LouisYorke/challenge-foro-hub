package com.challenge.foro_hub.domain.perfil;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="Perfil")
@Table(name = "perfil")
@Getter
@EqualsAndHashCode(of="id")
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
