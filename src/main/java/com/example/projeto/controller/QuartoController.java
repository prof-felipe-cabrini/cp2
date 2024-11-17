package com.example.projeto.controller;

import com.example.projeto.dto.QuartoDTO;
import com.example.projeto.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity<QuartoDTO> createQuarto(@Valid @RequestBody QuartoDTO quartoDTO) {
        return ResponseEntity.ok(quartoService.createQuarto(quartoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<QuartoDTO>> getQuartos(@RequestParam(required = false) String tipo,
                                                      @RequestParam(required = false) Long hotelId,
                                                      Pageable pageable) {
        return ResponseEntity.ok(quartoService.getQuartos(tipo, hotelId, pageable));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<Page<QuartoDTO>> getQuartosDisponiveis(@RequestParam String data, Pageable pageable) {
        return ResponseEntity.ok(quartoService.getQuartosDisponiveis(data, pageable));
    }
}