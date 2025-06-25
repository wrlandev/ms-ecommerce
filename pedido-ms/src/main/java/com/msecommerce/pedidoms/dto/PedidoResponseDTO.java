package com.msecommerce.pedidoms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID id,
        UUID clienteId,
        BigDecimal total,
        LocalDateTime dataPedido,
        List<ItemPedidoResponseDTO> itens
) {}