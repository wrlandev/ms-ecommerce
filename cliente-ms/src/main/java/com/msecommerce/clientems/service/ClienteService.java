package com.msecommerce.clientems.service;

import com.msecommerce.clientems.dto.ClienteRequestDTO;
import com.msecommerce.clientems.dto.ClienteResponseDTO;
import com.msecommerce.clientems.entity.Cliente;
import com.msecommerce.clientems.exception.RecursoNaoEncontradoException;
import com.msecommerce.clientems.mapper.ClienteMapper;
import com.msecommerce.clientems.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteRequestDTO);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarClientePorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);

        return clientes.map(clienteMapper::toResponseDTO);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(UUID id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));

        clienteMapper.updateEntityFromDTO(clienteRequestDTO, clienteExistente);
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);

        return clienteMapper.toResponseDTO(clienteAtualizado);
    }

    @Transactional
    public void deletarCliente(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}