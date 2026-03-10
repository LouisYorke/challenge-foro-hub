package com.challenge.foro_hub.controller;

import com.challenge.foro_hub.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    //Registrar los topicos
    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleTopic> registrar(@RequestBody @Valid DatosRegistroTopic datos) {
        DatosDetalleTopic respuesta = topicService.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
    //Listar todos los topicos
    @GetMapping
    public ResponseEntity<List<DatosDetalleTopic>> listar() {
        List<DatosDetalleTopic> lista = topicService.listar();
        return ResponseEntity.ok(lista);
    }
    //Se lista los primeros 10 por fehca
    @GetMapping("/ordenados")
    public ResponseEntity<List<DatosDetalleTopic>> listarPrimeros10PorFechaAsc() {
        List<DatosDetalleTopic> lista = topicService.listarPrimeros10PorFechaAsc();
        return ResponseEntity.ok(lista);
    }
    //Se filtra por cursos y años seleccionados
    @GetMapping("/filtrar")
    public ResponseEntity<List<DatosDetalleTopic>> filtrarPorCursoYAnio(
            @RequestParam String nombreCurso,
            @RequestParam int anio) {
        return ResponseEntity.ok(topicService.filtrarPorCursoYAnio(nombreCurso, anio));
    }
    //Usado para paginados
    @GetMapping("/paginados")
    public ResponseEntity<Page<DatosDetalleTopic>> listarPaginado(
            @PageableDefault(size = 5, sort = "fechaCreacion") Pageable pageable) {
        return ResponseEntity.ok(topicService.listarPaginado(pageable));
    }
    //Detallar por ID
    @GetMapping ("/{id}")
    public ResponseEntity<DatosDetalleTopic> obtenerTopicPorId(@PathVariable Long id) {
        var detalle = topicService.obtenerPorId(id);
        return ResponseEntity.ok(detalle);
    }
    //Actualizar ID
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> actualizarTopic(@PathVariable Long id, @RequestBody DatosActualizarTopic datos) {
        topicService.actualizar(id, datos);
        return ResponseEntity.noContent().build();
    }
    //Eliminar ID
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopic(@PathVariable Long id) {
        topicService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
