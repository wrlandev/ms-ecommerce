package com.msecommerce.pedidoms.service;

import com.msecommerce.pedidoms.client.ClienteClient;
import com.msecommerce.pedidoms.client.ProdutoClient;
import com.msecommerce.pedidoms.client.dto.AtualizarEstoqueRequestDTO;
import com.msecommerce.pedidoms.client.dto.ProdutoResponseDTO;
import com.msecommerce.pedidoms.dto.ItemPedidoRequestDTO;
import com.msecommerce.pedidoms.dto.PedidoRequestDTO;
import com.msecommerce.pedidoms.dto.PedidoResponseDTO;
import com.msecommerce.pedidoms.entity.ItemPedido;
import com.msecommerce.pedidoms.entity.Pedido;
import com.msecommerce.pedidoms.exception.EstoqueInsuficienteException;
import com.msecommerce.pedidoms.exception.RecursoNaoEncontradoException;
import com.msecommerce.pedidoms.mapper.PedidoMapper;
import com.msecommerce.pedidoms.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        clienteClient.buscarClientePorId(pedidoRequestDTO.clienteId());

        BigDecimal totalPedido = BigDecimal.ZERO;
        List<ItemPedido> itensPedido = new ArrayList<>();
        Pedido pedido = new Pedido();

        for (ItemPedidoRequestDTO itemDTO : pedidoRequestDTO.itens()) {
            ProdutoResponseDTO produto = produtoClient.buscarProdutoPorId(itemDTO.produtoId());

            if (produto.estoque() < itemDTO.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto ID: " + produto.id());
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProdutoId(produto.id());
            itemPedido.setQuantidade(itemDTO.quantidade());
            itemPedido.setPrecoUnitario(produto.preco());
            itemPedido.setPedido(pedido);
            itensPedido.add(itemPedido);

            totalPedido = totalPedido.add(produto.preco().multiply(BigDecimal.valueOf(itemDTO.quantidade())));
        }

        pedido.setClienteId(pedidoRequestDTO.clienteId());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(totalPedido);
        pedido.setItens(itensPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for(ItemPedido item : pedidoSalvo.getItens()){
            produtoClient.debitarEstoque(item.getProdutoId(), new AtualizarEstoqueRequestDTO(item.getQuantidade()));
        }

        String mensagemEvento = "Pedido " + pedidoSalvo.getId() + " criado para o cliente " + pedidoSalvo.getClienteId();
        kafkaProducerService.enviarEventoPedidoCriado(mensagemEvento);

        return pedidoMapper.toResponseDTO(pedidoSalvo);
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPedidoPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com o ID: " + id));
        return pedidoMapper.toResponseDTO(pedido);
    }
}