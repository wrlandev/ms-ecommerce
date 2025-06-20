package com.msecommerce.clientems.mapper;

import com.msecommerce.clientems.dto.ClienteRequestDTO;
import com.msecommerce.clientems.dto.ClienteResponseDTO;
import com.msecommerce.clientems.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toResponseDTO(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO clienteRequestDTO);

    void updateEntityFromDTO(ClienteRequestDTO clienteRequestDTO, @MappingTarget Cliente cliente);
}