package com.msecommerce.produtoms.mapper;

import com.msecommerce.produtoms.dto.ProdutoRequestDTO;
import com.msecommerce.produtoms.dto.ProdutoResponseDTO;
import com.msecommerce.produtoms.entity.Produto;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-25T14:03:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        String descricao = null;
        BigDecimal preco = null;
        Integer estoque = null;

        id = produto.getId();
        nome = produto.getNome();
        descricao = produto.getDescricao();
        preco = produto.getPreco();
        estoque = produto.getEstoque();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO( id, nome, descricao, preco, estoque );

        return produtoResponseDTO;
    }

    @Override
    public Produto toEntity(ProdutoRequestDTO produtoRequestDTO) {
        if ( produtoRequestDTO == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setNome( produtoRequestDTO.nome() );
        produto.setDescricao( produtoRequestDTO.descricao() );
        produto.setPreco( produtoRequestDTO.preco() );
        produto.setEstoque( produtoRequestDTO.estoque() );

        return produto;
    }

    @Override
    public void updateEntityFromDTO(ProdutoRequestDTO produtoRequestDTO, Produto produto) {
        if ( produtoRequestDTO == null ) {
            return;
        }

        produto.setNome( produtoRequestDTO.nome() );
        produto.setDescricao( produtoRequestDTO.descricao() );
        produto.setPreco( produtoRequestDTO.preco() );
        produto.setEstoque( produtoRequestDTO.estoque() );
    }
}
