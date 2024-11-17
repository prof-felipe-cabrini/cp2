package com.example.projeto.service;

import com.example.projeto.dto.QuartoDTO;
import com.example.projeto.model.Hotel;
import com.example.projeto.model.Quarto;
import com.example.projeto.repository.HotelRepository;
import com.example.projeto.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public QuartoDTO createQuarto(QuartoDTO quartoDTO) {
        Hotel hotel = hotelRepository.findById(quartoDTO.getHotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        Quarto quarto = new Quarto(quartoDTO.getNumero(), quartoDTO.getTipo(), hotel);
        return convertToDTO(quartoRepository.save(quarto));
    }

    public Page<QuartoDTO> getQuartos(String tipo, Long hotelId, Pageable pageable) {
        if (hotelId != null) {
            Hotel hotel = hotelRepository.findById(hotelId)
                    .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
            return quartoRepository.findAllByTipoAndHotel(tipo, hotel, pageable).map(this::convertToDTO);
        }
        return quartoRepository.findAll(pageable).map(this::convertToDTO);
    }

    public Page<QuartoDTO> getQuartosDisponiveis(String data, Pageable pageable) {
        LocalDate availableDate = LocalDate.parse(data);
        return quartoRepository.findQuartosDisponiveis(availableDate, pageable).map(this::convertToDTO);
    }

    private QuartoDTO convertToDTO(Quarto quarto) {
        return new QuartoDTO(quarto.getId(), quarto.getNumero(), quarto.getTipo(), quarto.getHotel().getId());
    }
}