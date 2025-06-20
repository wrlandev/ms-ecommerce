package com.msecommerce.clientems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio") // Validação: o campo não pode ser nulo ou conter apenas espaços em branco.
        String nome,

        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "Formato de email inválido") // Validação: o campo deve ser um email bem formado.
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        String senha
) {}
