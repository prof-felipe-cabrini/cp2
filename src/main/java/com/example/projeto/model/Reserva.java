package com.example.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "reserva_quarto",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "quarto_id"))
    private List<Quarto> quartos;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoServico> pedidosServico;

    // Construtores, getters e setters
    public Reserva() {
    }

    public Reserva(LocalDate dataCheckIn, LocalDate dataCheckOut, Cliente cliente, List<Quarto> quartos) {
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.cliente = cliente;
        this.quartos = quartos;
    }

    // Getters e Setters
}