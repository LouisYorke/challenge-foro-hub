package com.challenge.foro_hub.domain.topic;

import java.time.LocalDateTime;

public record DatosDetalleTopic(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopic status,
        String usuario,
        String curso
) {
    public DatosDetalleTopic(Topic topic){
        this(topic.getId(),topic.getTitulo(), topic.getMensaje(),topic.getFechaCreacion(),topic.getStatus(),topic.getUsuario().getNombre(),topic.getCurso().getNombre());
    }
}
