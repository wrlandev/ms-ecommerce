package com.msecommerce.produtoms.mapper;

import com.msecommerce.produtoms.dto.ProdutoRequestDTO;
import com.msecommerce.produtoms.dto.ProdutoResponseDTO;
import com.msecommerce.produtoms.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoResponseDTO toResponseDTO(Produto produto);
    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);
    void updateEntityFromDTO(ProdutoRequestDTO produtoRequestDTO, @MappingTarget Produto produto);
}