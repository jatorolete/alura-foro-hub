package com.alura.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findByCurso_Nombre(String nombreCurso);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    List<Topico> buscarPorAnio(int anio);
}