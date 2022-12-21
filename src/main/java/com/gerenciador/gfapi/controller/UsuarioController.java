package com.gerenciador.gfapi.controller;

import com.gerenciador.gfapi.model.dto.UsuarioDTO;
import com.gerenciador.gfapi.model.entity.Usuario;
import com.gerenciador.gfapi.service.UsuarioService;
import com.gerenciador.gfapi.util.Formatacao;
import com.gerenciador.gfapi.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        if (usuarioService.existsByUsername(usuarioDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: nome de usuário já está em uso.");
        }

        String cpf = Formatacao.limparCpf(usuarioDTO.getCpf());
        if (!Utils.valida_cpf(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problema: o CPF não é válido.");
        } else {
            usuarioDTO.setCpf(cpf);
        }

        if (usuarioService.existsByCpf(usuarioDTO.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: este CPF já está em uso.");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setDtaAdd(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> retornarTodosUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retornarUsuarioPorId(@PathVariable(value = "id") long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarUsuario(@PathVariable(value = "id") long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        String cpf = Formatacao.limparCpf(usuarioDTO.getCpf());
        if (!Utils.valida_cpf(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problema: o CPF não é válido.");
        } else {
            usuarioDTO.setCpf(cpf);
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setId(usuarioOptional.get().getId());
        usuario.setUsername(usuarioOptional.get().getUsername());
        usuario.setDtaAdd(usuarioOptional.get().getDtaAdd());
        usuario.setDtaAlt(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.salvar(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioService.deletar(usuarioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }
}
