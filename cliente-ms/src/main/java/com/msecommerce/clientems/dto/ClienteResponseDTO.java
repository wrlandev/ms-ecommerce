package com.msecommerce.clientems.dto;

import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String nome,
        String email
) {}
