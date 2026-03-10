    package com.challenge.foro_hub.domain.topic;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface TopicRepository extends JpaRepository<Topic, Long> {
        boolean existsByTituloAndMensaje(String titulo, String mensaje);

        @Query("SELECT t FROM Topic t WHERE t.curso.nombre = :nombreCurso AND YEAR(t.fechaCreacion) = :anio")
        List<Topic> buscarPorCursoYAnio(@Param("nombreCurso") String nombreCurso, @Param("anio") int anio);


    }
