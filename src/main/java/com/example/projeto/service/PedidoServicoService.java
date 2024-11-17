package com.example.projeto.service;

import com.example.projeto.dto.PedidoServicoDTO;
import com.example.projeto.model.PedidoServico;
import com.example.projeto.model.Reserva;
import com.example.projeto.model.Servico;
import com.example.projeto.repository.PedidoServicoRepository;
import com.example.projeto.repository.ReservaRepository;
import com.example.projeto.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServicoService {

    @Autowired
    private PedidoServicoRepository pedidoServicoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public PedidoServicoDTO createPedidoServico(PedidoServicoDTO pedidoServicoDTO) {
        Reserva reserva = reservaRepository.findById(pedidoServicoDTO.getReservaId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        List<Servico> servicos = pedidoServicoDTO.getServicosId().stream()
                .map(id -> servicoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Service not found")))
                .collect(Collectors.toList());

        PedidoServico pedidoServico = new PedidoServico(pedidoServicoDTO.getDataPedido(), reserva, servicos);
        return convertToDTO(pedidoServicoRepository.save(pedidoServico));
    }

    public Page<PedidoServicoDTO> getAllPedidosServico(Long reservaId, Pageable pageable) {
        if (reservaId != null) {
            return pedidoServicoRepository.findAllByReservaId(reservaId, pageable).map(this::convertToDTO);
        }
        return pedidoServicoRepository.findAll(pageable).map(this::convertToDTO);
    }

    private PedidoServicoDTO convertToDTO(PedidoServico pedidoServico) {
        List<Long> servicosId = pedidoServico.getServicos().stream().map(Servico::getId).collect(Collectors.toList());
        return new PedidoServicoDTO(pedidoServico.getId(), pedidoServico.getDataPedido(), pedidoServico.getReserva().getId(), servicosId);
    }
}