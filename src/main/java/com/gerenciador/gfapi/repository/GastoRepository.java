package com.gerenciador.gfapi.repository;

import com.gerenciador.gfapi.model.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    List<Gasto> findByIdUsuario(long idUsuario);
}
