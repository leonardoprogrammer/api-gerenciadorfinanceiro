package com.gerenciador.gfapi.service;

import com.gerenciador.gfapi.model.entity.Gasto;
import com.gerenciador.gfapi.repository.GastoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    @Transactional
    public Gasto salvar(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public List<Gasto> findAll() {
        return gastoRepository.findAll();
    }

    public Optional<Gasto> findById(long id) {
        return gastoRepository.findById(id);
    }

    public List<Gasto> findByIdUsuario(long id) {
        return gastoRepository.findByIdUsuario(id);
    }

    @Transactional
    public void deletar(Gasto gasto) {
        gastoRepository.delete(gasto);
    }
}
