package com.example.projeto.controller;

import com.example.projeto.dto.ClienteDTO;
import com.example.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.createCliente(clienteDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAllClientes(Pageable pageable) {
        return ResponseEntity.ok(clienteService.getAllClientes(pageable));
    }
}