package com.msecommerce.produtoms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AtualizarEstoqueRequestDTO(
        @NotNull
        @Min(value = 1, message = "A quantidade a ser debitada deve ser de no mínimo 1")
        Integer quantidade
) {}
