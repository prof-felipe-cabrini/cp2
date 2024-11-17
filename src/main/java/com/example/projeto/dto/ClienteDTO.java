package com.example.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório.")
    @Size(max = 100, message = "O nome do cliente não pode ter mais de 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email do cliente é obrigatório.")
    @Email(message = "O email do cliente deve ser válido.")
    private String email;

    @NotBlank(message = "O documento do cliente é obrigatório.")
    @Size(max = 20, message = "O documento do cliente não pode ter mais de 20 caracteres.")
    @CPF(message = "O CPF do cliente deve ser válido.")
    private String documento;


}