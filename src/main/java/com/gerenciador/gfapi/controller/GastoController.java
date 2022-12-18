package com.gerenciador.gfapi.controller;

import com.gerenciador.gfapi.model.dto.GastoDTO;
import com.gerenciador.gfapi.model.entity.Gasto;
import com.gerenciador.gfapi.service.GastoService;
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
@RequestMapping("/gasto")
public class GastoController {

    private GastoService gastoService;
    private UsuarioService usuarioService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarGasto(@RequestBody @Valid GastoDTO gastoDTO) {
        if (!usuarioService.existsById(gastoDTO.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: usuário não encontrado.");
        }

        Gasto gasto = new Gasto();
        BeanUtils.copyProperties(gastoDTO, gasto);
        gasto.setDtaAdd(Calendar.getInstance());

        return ResponseEntity.status(HttpStatus.CREATED).body(gastoService.salvar(gasto));
    }

    @GetMapping("/usuario/id") //trocar para QueryParam
    public ResponseEntity<List<Gasto>> enviarGastosPorUsuario(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.findByIdUsuario(id));
    }
}
