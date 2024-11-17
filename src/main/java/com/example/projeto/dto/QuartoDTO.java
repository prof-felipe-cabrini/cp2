package com.example.projeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuartoDTO {

    private Long id;

    @NotBlank(message = "O número do quarto é obrigatório.")
    @Size(max = 10, message = "O número do quarto não pode ter mais de 10 caracteres.")
    private String numero;

    @NotBlank(message = "O tipo de quarto é obrigatório.")
    @Size(max = 20, message = "O tipo do quarto não pode ter mais de 20 caracteres.")
    private String tipo;

    @NotNull(message = "O ID do hotel é obrigatório.")
    private Long hotelId;

}