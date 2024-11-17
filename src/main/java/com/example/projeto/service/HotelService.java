package com.example.projeto.service;

import com.example.projeto.dto.HotelDTO;
import com.example.projeto.model.Hotel;
import com.example.projeto.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(hotelDTO.getNome(), hotelDTO.getEndereco());
        return convertToDTO(hotelRepository.save(hotel));
    }

    public Page<HotelDTO> getAllHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        if (!hotel.getQuartos().isEmpty()) {
            throw new IllegalStateException("Cannot delete hotel with associated rooms.");
        }
        hotelRepository.delete(hotel);
    }

    private HotelDTO convertToDTO(Hotel hotel) {
        return new HotelDTO(hotel.getId(), hotel.getNome(), hotel.getEndereco());
    }
}