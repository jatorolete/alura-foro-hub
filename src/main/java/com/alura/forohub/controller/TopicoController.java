package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // POST: Registrar tópico
    // ----------------------
    @PostMapping
    @Transactional
    public String registrar(@RequestBody @Valid DatosRegistroTopico datos){

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return "Error: Ya existe un tópico con este titulo y mensaje";
        }

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no se ha encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no ha sido encontrado"));

        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                autor,
                curso
        );

        topicoRepository.save(topico);

        return "El tópico ha sido registrado correctamente";
    }


    // GET: Listado con ordenamiento y filtros
    // --------------------------------------
    @GetMapping
    public List<DatosListadoTopico> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) String orden){

        var ordenamiento = (orden != null && orden.equalsIgnoreCase("asc"))
                ? org.springframework.data.domain.Sort.by("fechaCreacion").ascending()
                : org.springframework.data.domain.Sort.by("fechaCreacion").descending();

        List<Topico> topicos;

        if (curso != null) {
            topicos = topicoRepository.findByCurso_Nombre(curso);
        } else if (anio != null) {
            topicos = topicoRepository.buscarPorAnio(anio);
        } else {
            topicos = topicoRepository.findAll(ordenamiento);
        }
        return topicos.stream()
                .map(DatosListadoTopico::new)
                .toList();
    }

    // GET: Paginación
    // ---------------
    @GetMapping("/paginado")
    public Object listarPaginado(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion
    ) {
        return topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosDetalleTopico detalle(@PathVariable Long id) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return new DatosDetalleTopico(topico);
    }

    // actualizar topico
    @PutMapping("/{id}")
    @Transactional
    public DatosDetalleTopico actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos
    ) {

        if (!id.equals(datos.id())) {
            throw new RuntimeException("El ID de la URL no coincide con el ID del cuerpo");
        }

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.actualizar(datos, curso);

        return new DatosDetalleTopico(topico);
    }

    //Eliminar topico
    @DeleteMapping("/{id}")
    @Transactional
    public String eliminar(@PathVariable Long id) {

        var topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            throw new RuntimeException("Tópico no encontrado");
        }

        topicoRepository.deleteById(id);

        return "Tópico eliminado correctamente";
    }
}