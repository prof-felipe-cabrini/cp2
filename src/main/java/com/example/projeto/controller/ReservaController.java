package com.example.projeto.controller;

import com.example.projeto.dto.ReservaDTO;
import com.example.projeto.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.createReserva(reservaDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReservaDTO>> getReservas(@RequestParam(required = false) Long clienteId,
                                                        @RequestParam(required = false) String dataInicio,
                                                        @RequestParam(required = false) String dataFim,
                                                        Pageable pageable) {
        return ResponseEntity.ok(reservaService.getReservas(clienteId, dataInicio, dataFim, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}