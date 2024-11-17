package com.example.projeto.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoServicoDTO {

    private Long id;

    @NotNull(message = "A data do pedido de serviço é obrigatória.")
    @FutureOrPresent(message = "A data do pedido de serviço não pode estar no passado.")
    private LocalDateTime dataPedido;

    @NotNull(message = "O ID da reserva é obrigatório.")
    private Long reservaId;

    @NotNull(message = "A lista de serviços é obrigatória.")
    @Size(min = 1, message = "O pedido de serviço deve incluir pelo menos um serviço.")
    private List<Long> servicosId;

}