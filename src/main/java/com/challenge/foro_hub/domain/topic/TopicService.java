package com.challenge.foro_hub.domain.topic;

import com.challenge.foro_hub.domain.curso.Curso;
import com.challenge.foro_hub.domain.curso.CursoRepository;
import com.challenge.foro_hub.domain.usuario.Usuario;
import com.challenge.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //Registrar nuevo topico
    public DatosDetalleTopic registrar(DatosRegistroTopic datos) {
        Usuario usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        boolean existe = topicRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existe) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tópico con el mismo título y mensaje.");
        }
        Topic topic = new Topic(datos.titulo(),datos.mensaje(),LocalDateTime.now(),StatusTopic.ABIERTO,usuario,curso);

        topicRepository.save(topic);

        return new DatosDetalleTopic(topic);
    }

    //Se lista
    public List<DatosDetalleTopic> listar() {
        return topicRepository.findAll().stream()
                .map(DatosDetalleTopic::new)
                .toList();
    }

    public List<DatosDetalleTopic> listarPrimeros10PorFechaAsc() {
        Pageable top10Asc = PageRequest.of(0, 10, Sort.by("fechaCreacion").ascending());
        return topicRepository.findAll(top10Asc).getContent().stream()
                .map(DatosDetalleTopic::new)
                .toList();
    }

    public List<DatosDetalleTopic> filtrarPorCursoYAnio(String nombreCurso, int anio) {
        return topicRepository.buscarPorCursoYAnio(nombreCurso, anio).stream()
                .map(DatosDetalleTopic::new)
                .toList();
    }

    public Page<DatosDetalleTopic> listarPaginado(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(DatosDetalleTopic::new);
    }

    public DatosDetalleTopic obtenerPorId(Long id) {
        var topic = topicRepository.getReferenceById(id);
        return new DatosDetalleTopic(topic);
    }
    //Actualizar topico
    @Transactional
    public void actualizar(Long id, DatosActualizarTopic datos) {
        if (!topicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
        Topic topic = topicRepository.getReferenceById(id);
        Curso curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        topic.setTitulo(datos.titulo());
        topic.setMensaje(datos.mensaje());
        topic.setStatus(datos.status());
        topic.setCurso(curso);
        new DatosDetalleTopic(topic);
    }
    @Transactional
    public void eliminar(Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);

        if (topicOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

        topicRepository.deleteById(id);
    }
}
