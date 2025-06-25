package com.msecommerce.pedidoms.mapper;

import com.msecommerce.pedidoms.dto.PedidoResponseDTO;
import com.msecommerce.pedidoms.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponseDTO toResponseDTO(Pedido pedido);

}