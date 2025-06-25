package com.msecommerce.pedidoms.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponseDTO(
        Long id,
        UUID produtoId,
        Integer quantidade,
        BigDecimal precoUnitario
) {}