package com.gerenciador.gfapi.repository;

import com.gerenciador.gfapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsById(long id);

    boolean existsByUsername(String username);

    boolean existsAllByCpf(String cpf);

    Optional<Usuario> findById(long id);
}
