package com.challenge.foro_hub.domain.respuesta;


import com.challenge.foro_hub.domain.topic.Topic;
import com.challenge.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name="Respuesta")
@Table(name = "respuesta")
@Getter
@Setter
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topic topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Usuario autor;
}
