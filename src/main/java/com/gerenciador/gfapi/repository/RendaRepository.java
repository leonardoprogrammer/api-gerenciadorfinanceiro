package com.gerenciador.gfapi.repository;

import com.gerenciador.gfapi.model.entity.Renda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Long> {

    List<Renda> findByIdUsuario(long idUsuario);
}
