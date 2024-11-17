package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal preco;

    // Construtores, getters e setters
    public Servico() {
    }

    public Servico(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters
}