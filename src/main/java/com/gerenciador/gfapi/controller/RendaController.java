package com.gerenciador.gfapi.controller;

import com.gerenciador.gfapi.model.dto.RendaDTO;
import com.gerenciador.gfapi.model.entity.Renda;
import com.gerenciador.gfapi.service.RendaService;
import com.gerenciador.gfapi.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/renda")
public class RendaController {

    private RendaService rendaService;
    private UsuarioService usuarioService;

    public RendaController(RendaService rendaService, UsuarioService usuarioService) {
        this.rendaService = rendaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarRenda(@RequestBody @Valid RendaDTO rendaDTO) {
        if (!usuarioService.existsById(rendaDTO.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: usuário não encontrado.");
        }

        Renda renda = new Renda();
        BeanUtils.copyProperties(rendaDTO, renda);
        renda.setDtaAdd(Calendar.getInstance());

        return ResponseEntity.status(HttpStatus.CREATED).body(rendaService.salvar(renda));
    }

    @GetMapping
    public ResponseEntity<Object> enviarRendasPorUsuario(@RequestParam(name = "usuario") String id) {
        id = id.replaceAll("[^0-9]", "");

        if (id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: o id do usuário deve ser numérico.");
        }

        Long idUsuario = Long.parseLong(id);

        return ResponseEntity.status(HttpStatus.OK).body(rendaService.findByIdUsuario(idUsuario));
    }
}
