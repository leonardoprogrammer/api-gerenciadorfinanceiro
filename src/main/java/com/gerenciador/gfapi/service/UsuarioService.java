package com.gerenciador.gfapi.service;

import com.gerenciador.gfapi.model.entity.Usuario;
import com.gerenciador.gfapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional //caso der errado no salvamento, essa anotação garante o rollback
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void deletar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public boolean existsById(long id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existsByCpf(String cpf) {
        return usuarioRepository.existsAllByCpf(cpf);
    }
}
