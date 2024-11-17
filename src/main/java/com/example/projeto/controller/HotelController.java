package com.example.projeto.controller;

import com.example.projeto.dto.HotelDTO;
import com.example.projeto.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hoteis")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<HotelDTO>> getAllHotels(Pageable pageable) {
        return ResponseEntity.ok(hotelService.getAllHotels(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}