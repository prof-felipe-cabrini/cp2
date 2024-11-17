package com.example.projeto.service;

import com.example.projeto.dto.ClienteDTO;
import com.example.projeto.model.Cliente;
import com.example.projeto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getDocumento());
        return convertToDTO(clienteRepository.save(cliente));
    }

    public Page<ClienteDTO> getAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(this::convertToDTO);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getDocumento());
    }
}