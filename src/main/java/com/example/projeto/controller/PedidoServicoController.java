package com.example.projeto.controller;

import com.example.projeto.dto.PedidoServicoDTO;
import com.example.projeto.service.PedidoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos-servico")
public class PedidoServicoController {

    @Autowired
    private PedidoServicoService pedidoServicoService;

    @PostMapping
    public ResponseEntity<PedidoServicoDTO> createPedidoServico(@Valid @RequestBody PedidoServicoDTO pedidoServicoDTO) {
        return ResponseEntity.ok(pedidoServicoService.createPedidoServico(pedidoServicoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoServicoDTO>> getAllPedidosServico(@RequestParam(required = false) Long reservaId,
                                                                       Pageable pageable) {
        return ResponseEntity.ok(pedidoServicoService.getAllPedidosServico(reservaId, pageable));
    }
}