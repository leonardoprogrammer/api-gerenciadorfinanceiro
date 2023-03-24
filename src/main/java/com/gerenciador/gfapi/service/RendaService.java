package com.gerenciador.gfapi.service;

import com.gerenciador.gfapi.model.entity.Renda;
import com.gerenciador.gfapi.repository.RendaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RendaService {

    final RendaRepository rendaRepository;

    public RendaService(RendaRepository rendaRepository) {
        this.rendaRepository = rendaRepository;
    }

    @Transactional
    public Renda salvar(Renda renda) {
        return rendaRepository.save(renda);
    }

    @Transactional
    public void deletar(Renda renda) {
        rendaRepository.delete(renda);
    }

    public List<Renda> findAll() {
        return rendaRepository.findAll();
    }

    public Optional<Renda> findById(long id) {
        return rendaRepository.findById(id);
    }

    public List<Renda> findByIdUsuario(long id) {
        return rendaRepository.findByIdUsuario(id);
    }

    public boolean existsByIdUsuario(long idUsuario) {
        return rendaRepository.existsByIdUsuario(idUsuario);
    }
}
