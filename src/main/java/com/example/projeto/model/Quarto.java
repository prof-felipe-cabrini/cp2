package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(mappedBy = "quartos")
    private List<Reserva> reservas;

    // Construtores, getters e setters
    public Quarto() {
    }

    public Quarto(String numero, String tipo, Hotel hotel) {
        this.numero = numero;
        this.tipo = tipo;
        this.hotel = hotel;
    }

    // Getters e Setters
}