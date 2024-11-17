package com.example.projeto.controller;

import com.example.projeto.dto.ServicoDTO;
import com.example.projeto.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@Valid @RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.createServico(servicoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ServicoDTO>> getAllServicos(Pageable pageable) {
        return ResponseEntity.ok(servicoService.getAllServicos(pageable));
    }
}