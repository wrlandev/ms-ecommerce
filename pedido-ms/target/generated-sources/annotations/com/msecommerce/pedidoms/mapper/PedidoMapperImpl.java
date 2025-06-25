package com.msecommerce.pedidoms.mapper;

import com.msecommerce.pedidoms.dto.ItemPedidoResponseDTO;
import com.msecommerce.pedidoms.dto.PedidoResponseDTO;
import com.msecommerce.pedidoms.entity.ItemPedido;
import com.msecommerce.pedidoms.entity.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-25T14:03:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        UUID id = null;
        UUID clienteId = null;
        BigDecimal total = null;
        LocalDateTime dataPedido = null;
        List<ItemPedidoResponseDTO> itens = null;

        id = pedido.getId();
        clienteId = pedido.getClienteId();
        total = pedido.getTotal();
        dataPedido = pedido.getDataPedido();
        itens = itemPedidoListToItemPedidoResponseDTOList( pedido.getItens() );

        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO( id, clienteId, total, dataPedido, itens );

        return pedidoResponseDTO;
    }

    protected ItemPedidoResponseDTO itemPedidoToItemPedidoResponseDTO(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }

        Long id = null;
        UUID produtoId = null;
        Integer quantidade = null;
        BigDecimal precoUnitario = null;

        id = itemPedido.getId();
        produtoId = itemPedido.getProdutoId();
        quantidade = itemPedido.getQuantidade();
        precoUnitario = itemPedido.getPrecoUnitario();

        ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO( id, produtoId, quantidade, precoUnitario );

        return itemPedidoResponseDTO;
    }

    protected List<ItemPedidoResponseDTO> itemPedidoListToItemPedidoResponseDTOList(List<ItemPedido> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemPedidoResponseDTO> list1 = new ArrayList<ItemPedidoResponseDTO>( list.size() );
        for ( ItemPedido itemPedido : list ) {
            list1.add( itemPedidoToItemPedidoResponseDTO( itemPedido ) );
        }

        return list1;
    }
}
