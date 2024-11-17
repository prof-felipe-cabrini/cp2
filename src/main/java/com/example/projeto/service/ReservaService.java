package com.example.projeto.service;

import com.example.projeto.dto.ReservaDTO;
import com.example.projeto.model.Cliente;
import com.example.projeto.model.Quarto;
import com.example.projeto.model.Reserva;
import com.example.projeto.repository.ClienteRepository;
import com.example.projeto.repository.QuartoRepository;
import com.example.projeto.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Transactional
    public ReservaDTO createReserva(ReservaDTO reservaDTO) {
        Cliente cliente = clienteRepository.findById(reservaDTO.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        List<Quarto> quartos = reservaDTO.getQuartosId().stream()
                .map(id -> quartoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Room not found")))
                .collect(Collectors.toList());

        Reserva reserva = new Reserva(reservaDTO.getDataCheckIn(), reservaDTO.getDataCheckOut(), cliente, quartos);
        return convertToDTO(reservaRepository.save(reserva));
    }

    public Page<ReservaDTO> getReservas(Long clienteId, String dataInicio, String dataFim, Pageable pageable) {
        LocalDate startDate = dataInicio != null ? LocalDate.parse(dataInicio) : null;
        LocalDate endDate = dataFim != null ? LocalDate.parse(dataFim) : null;
        return reservaRepository.findReservas(clienteId, startDate, endDate, pageable).map(this::convertToDTO);
    }

    @Transactional
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        List<Long> quartosId = reserva.getQuartos().stream().map(Quarto::getId).collect(Collectors.toList());
        return new ReservaDTO(reserva.getId(), reserva.getDataCheckIn(), reserva.getDataCheckOut(), reserva.getCliente().getId(), quartosId);
    }
}