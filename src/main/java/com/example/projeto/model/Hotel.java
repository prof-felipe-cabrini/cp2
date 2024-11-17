package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quarto> quartos;

    // Construtores, getters e setters
    public Hotel() {
    }

    public Hotel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters
}