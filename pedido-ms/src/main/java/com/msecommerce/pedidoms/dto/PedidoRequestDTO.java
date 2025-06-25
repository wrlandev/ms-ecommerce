package com.msecommerce.pedidoms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(

        @NotNull(message = "O ID do cliente é obrigatório.")
        UUID clienteId,

        @NotEmpty(message = "O pedido deve conter pelo menos um item.")
        @Valid
        List<ItemPedidoRequestDTO> itens
) {}