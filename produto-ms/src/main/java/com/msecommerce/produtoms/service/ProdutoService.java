package com.msecommerce.produtoms.service;

import com.msecommerce.produtoms.dto.AtualizarEstoqueRequestDTO;
import com.msecommerce.produtoms.dto.ProdutoRequestDTO;
import com.msecommerce.produtoms.dto.ProdutoResponseDTO;
import com.msecommerce.produtoms.entity.Produto;
import com.msecommerce.produtoms.exception.EstoqueInsuficienteException;
import com.msecommerce.produtoms.exception.RecursoNaoEncontradoException;
import com.msecommerce.produtoms.mapper.ProdutoMapper;
import com.msecommerce.produtoms.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoSalvo);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "produtos", key = "#id")
    public ProdutoResponseDTO buscarProdutoPorId(UUID id) {
        System.out.println("### BUSCANDO PRODUTO NO BANCO DE DADOS ###");
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(produtoMapper::toResponseDTO);
    }

    @Transactional
    @CachePut(value = "produtos", key = "#id")
    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
        produtoMapper.updateEntityFromDTO(produtoRequestDTO, produtoExistente);
        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return produtoMapper.toResponseDTO(produtoAtualizado);
    }

    @Transactional
    @CacheEvict(value = "produtos", key = "#id")
    public void deletarProduto(UUID id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    @CachePut(value = "produtos", key = "#id")
    public ProdutoResponseDTO debitarEstoque(UUID id, AtualizarEstoqueRequestDTO request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
        if (produto.getEstoque() < request.quantidade()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome() + ". Disponível: " + produto.getEstoque() + ", Solicitado: " + request.quantidade());
        }
        produto.setEstoque(produto.getEstoque() - request.quantidade());
        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAtualizado);
    }
}