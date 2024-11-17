package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class PedidoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToMany
    @JoinTable(name = "pedido_servico_servico",
            joinColumns = @JoinColumn(name = "pedido_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<Servico> servicos;

    // Construtores, getters e setters
    public PedidoServico() {
    }

    public PedidoServico(LocalDateTime dataPedido, Reserva reserva, List<Servico> servicos) {
        this.dataPedido = dataPedido;
        this.reserva = reserva;
        this.servicos = servicos;
    }

    // Getters e Setters
}