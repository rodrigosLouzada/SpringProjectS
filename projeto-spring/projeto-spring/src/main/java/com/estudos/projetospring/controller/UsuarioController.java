package com.estudos.projetospring.controller;

import com.estudos.projetospring.business.UsuarioService;
import com.estudos.projetospring.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }
}
