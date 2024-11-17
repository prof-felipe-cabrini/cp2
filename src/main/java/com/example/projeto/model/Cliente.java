package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String documento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    // Construtores, getters e setters
    public Cliente() {
    }

    public Cliente(String nome, String email, String documento) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    // Getters e Setters
}