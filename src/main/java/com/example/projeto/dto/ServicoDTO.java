package com.example.projeto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {

    private Long id;

    @NotBlank(message = "A descrição do serviço é obrigatória.")
    @Size(max = 100, message = "A descrição do serviço não pode ter mais de 100 caracteres.")
    private String descricao;

    @NotNull(message = "O preço do serviço é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do serviço deve ser maior que zero.")
    private BigDecimal preco;

}