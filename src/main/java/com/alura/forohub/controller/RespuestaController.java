package com.alura.forohub.controller;

import com.alura.forohub.domain.respuesta.*;
import com.alura.forohub.domain.topico.TopicoRepository;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public DatosDetalleRespuesta registrar(@RequestBody @Valid DatosRegistroRespuesta datos) {

        var topico = topicoRepository.getReferenceById(datos.topicoId());

        var usuario = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        var respuesta = new Respuesta(datos.mensaje(), topico, usuario);

        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);
    }

    @GetMapping
    public java.util.List<DatosDetalleRespuesta> listar() {
        return respuestaRepository.findAll()
                .stream()
                .map(DatosDetalleRespuesta::new)
                .toList();
    }

    //buscar respuestas por id
    @GetMapping("/{id}")
    public DatosDetalleRespuesta detalle(@PathVariable Long id) {

        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        return new DatosDetalleRespuesta(respuesta);
    }

    //actualizar respuesta
    @PutMapping("/{id}")
    public DatosDetalleRespuesta actualizar(
            @PathVariable Long id,
            @RequestBody DatosActualizarRespuesta datos
    ) {

        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuesta.actualizar(datos);

        return new DatosDetalleRespuesta(respuesta);
    }

    // eliminar respuesta
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuestaRepository.delete(respuesta);

        return "Respuesta eliminada correctamente";
    }
}