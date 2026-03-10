package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear usuario
    @PostMapping
    public DatosDetalleUsuario registrar(@RequestBody @Valid DatosRegistroUsuario datos) {

        var usuario = new Usuario(datos);

        // Encriptar contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));

        usuarioRepository.save(usuario);

        return new DatosDetalleUsuario(usuario);
    }

    // Listar usuarios
    @GetMapping
    public List<DatosDetalleUsuario> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(DatosDetalleUsuario::new)
                .toList();
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public DatosDetalleUsuario actualizar(@PathVariable Long id,
                                          @RequestBody DatosActualizarUsuario datos) {

        var usuario = usuarioRepository.getReferenceById(id);

        // Si viene nueva contraseña, encriptarla
        if (datos.contrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        }

        usuario.actualizarDatos(datos);

        return new DatosDetalleUsuario(usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}