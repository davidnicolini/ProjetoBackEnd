package com.github.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.primeiro_exemplo.PrimeiroExemploApplication;
import com.github.primeiro_exemplo.model.Produto;
import com.github.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.github.primeiro_exemplo.repository.ProdutoRepository;
import com.github.primeiro_exemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    public ModelMapper mapper = new ModelMapper();

    public ProdutoService(PrimeiroExemploApplication primeiroExemploApplication, ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> listarTodosProdutos() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> mapper.map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterProdutoPorId(Integer id) {
        produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("produto com Id:"+id+"não foi encontrado"));
        Optional<Produto> produto = produtoRepository.findById(id);
        ProdutoDTO produtoDTO = mapper.map(produto.get(), ProdutoDTO.class);
        return Optional.of(produtoDTO);
    }

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
       produtoDTO.setId(null);

       Produto produto = mapper.map(produtoDTO, Produto.class);
       produto = produtoRepository.save(produto);
       produtoDTO.setId(produto.getId());
       return produtoDTO;
    }

    public void deletarProduto(Integer id) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possivel deletar o produto com o id:"+id+ " produto não encontrado"));
        produtoRepository.delete(produtoExistente);
    }

    public ProdutoDTO atualizarProduto(Integer id, ProdutoDTO produtoDTO) {
        
        produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        produtoDTO.setId(id);
        Produto produto = mapper.map(produtoDTO, Produto.class);
        produtoRepository.save(produto);
        return produtoDTO;

    }

}
