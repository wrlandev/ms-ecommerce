package com.msecommerce.pedidoms.client;

import com.msecommerce.pedidoms.client.dto.AtualizarEstoqueRequestDTO;
import com.msecommerce.pedidoms.client.dto.ProdutoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "produto-ms")
public interface ProdutoClient {

    @GetMapping("/produtos/{id}")
    ProdutoResponseDTO buscarProdutoPorId(@PathVariable("id") UUID id);

    @PatchMapping("/produtos/{id}/debitar-estoque")
    ProdutoResponseDTO debitarEstoque(@PathVariable("id") UUID id, @RequestBody AtualizarEstoqueRequestDTO request);
}