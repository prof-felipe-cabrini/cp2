package com.example.projeto.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Long id;

    @NotNull(message = "A data de check-in é obrigatória.")
    @Future(message = "A data de check-in deve estar no futuro.")
    private LocalDate dataCheckIn;

    @NotNull(message = "A data de check-out é obrigatória.")
    @Future(message = "A data de check-out deve estar no futuro.")
    private LocalDate dataCheckOut;

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotNull(message = "A lista de quartos é obrigatória.")
    @Size(min = 1, message = "A reserva deve incluir pelo menos um quarto.")
    private List<Long> quartosId;

}