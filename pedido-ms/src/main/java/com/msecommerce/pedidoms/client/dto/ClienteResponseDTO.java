package com.msecommerce.pedidoms.client.dto;

import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String nome,
        String email
) {}