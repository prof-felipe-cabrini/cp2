package com.example.projeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private Long id;

    @NotBlank(message = "O nome do hotel é obrigatório.")
    @Size(max = 100, message = "O nome do hotel não pode ter mais de 100 caracteres.")
    private String nome;

    @NotBlank(message = "O endereço do hotel é obrigatório.")
    @Size(max = 200, message = "O endereço do hotel não pode ter mais de 200 caracteres.")
    private String endereco;

}