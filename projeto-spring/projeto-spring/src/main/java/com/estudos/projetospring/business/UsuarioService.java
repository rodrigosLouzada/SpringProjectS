package com.estudos.projetospring.business;

import com.estudos.projetospring.infrastructure.entity.Usuario;
import com.estudos.projetospring.infrastructure.exceptions.ConflictException;
import com.estudos.projetospring.infrastructure.exceptions.ResourceNotFoundException;
import com.estudos.projetospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    
    public Usuario salvarUsuario(Usuario usuario){
        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }


    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw  new ConflictException("Email já cadastrado: " + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" , e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }


    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email Não Encontrado " + email));
    }

    public Void deletaUsuarioPorEmail(String email){
        return usuarioRepository.deleteByEmail(email);
    }

}
