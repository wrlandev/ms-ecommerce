package com.msecommerce.pedidoms.controller;

import com.msecommerce.pedidoms.dto.PedidoRequestDTO;
import com.msecommerce.pedidoms.dto.PedidoResponseDTO;
import com.msecommerce.pedidoms.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO novoPedido = pedidoService.criarPedido(pedidoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedidoPorId(@PathVariable UUID id) {
        PedidoResponseDTO pedido = pedidoService.buscarPedidoPorId(id);

        return ResponseEntity.ok(pedido);
    }
}