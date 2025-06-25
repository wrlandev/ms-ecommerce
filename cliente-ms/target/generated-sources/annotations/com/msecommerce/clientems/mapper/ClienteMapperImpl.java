package com.msecommerce.clientems.mapper;

import com.msecommerce.clientems.dto.ClienteRequestDTO;
import com.msecommerce.clientems.dto.ClienteResponseDTO;
import com.msecommerce.clientems.entity.Cliente;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-20T21:03:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        String email = null;

        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO( id, nome, email );

        return clienteResponseDTO;
    }

    @Override
    public Cliente toEntity(ClienteRequestDTO clienteRequestDTO) {
        if ( clienteRequestDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNome( clienteRequestDTO.nome() );
        cliente.setEmail( clienteRequestDTO.email() );
        cliente.setSenha( clienteRequestDTO.senha() );

        return cliente;
    }

    @Override
    public void updateEntityFromDTO(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
        if ( clienteRequestDTO == null ) {
            return;
        }

        cliente.setNome( clienteRequestDTO.nome() );
        cliente.setEmail( clienteRequestDTO.email() );
        cliente.setSenha( clienteRequestDTO.senha() );
    }
}
