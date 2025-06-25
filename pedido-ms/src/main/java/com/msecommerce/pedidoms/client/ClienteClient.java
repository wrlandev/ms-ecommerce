package com.msecommerce.pedidoms.client;

import com.msecommerce.pedidoms.client.dto.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cliente-ms-client", url = "${application.client.url}")
public interface ClienteClient {

    @GetMapping("/clientes/{id}")
    ClienteResponseDTO buscarClientePorId(@PathVariable("id") UUID id);
}