package com.msecommerce.clientems.controller;

import com.msecommerce.clientems.dto.ClienteRequestDTO;
import com.msecommerce.clientems.dto.ClienteResponseDTO;
import com.msecommerce.clientems.service.ClienteService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO novoCliente = clienteService.criarCliente(clienteRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientes(
            @Parameter(
                    description = "Parâmetros de paginação e ordenação. Para ordenar, use o formato 'campo,direcao' (ex: 'nome,asc' ou 'email,desc').",
                    schema = @Schema(implementation = Pageable.class)
            )
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<ClienteResponseDTO> clientes = clienteService.listarClientes(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(@PathVariable UUID id) {
        ClienteResponseDTO cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable UUID id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id) {
        clienteService.deletarCliente(id);

        return ResponseEntity.noContent().build();
    }
}