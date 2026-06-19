package com.github.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.primeiro_exemplo.PrimeiroExemploApplication;
import com.github.primeiro_exemplo.model.Produto;
import com.github.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.github.primeiro_exemplo.repository.ProdutoRepository;
import com.github.primeiro_exemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {

    private final PrimeiroExemploApplication primeiroExemploApplication;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    ProdutoService(PrimeiroExemploApplication primeiroExemploApplication) {
        this.primeiroExemploApplication = primeiroExemploApplication;
    }

    /**
     * Método para Retorna uma lista de Produtos.
     * 
     * @return Lista de produtos.
     */
    public List<ProdutoDTO> obterTodos() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper()
        .map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Método que retorna o produto pelo seu Id.
     * 
     * @param id do produto que será localizado.
     * @return Retona um produto caso seja endontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        // Obtendo optional de produto pelo id.
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não encontrar, lança exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("produto com Id:"+id+"não encontrado");
        }
        // Convertendo o meu optional de produto em um produtoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // Criando e retornando um optional de produtoDTO
        return Optional.of(dto);
    }

    /**
     * Método para adicionar produto.
     * 
     * @param produto que será adicionado .
     * @return o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
        // Removendo id para conseguir fazer o cadastro
       produtoDTO.setId(null);

       // Criar um objeto de mapeamento.
       ModelMapper mapper = new ModelMapper();

       // Converter o nosso ProdutoDTO em um Produto. 
       Produto produto = mapper.map(produtoDTO, Produto.class);

       // Salvar o produto do banco.
       produto = produtoRepository.save(produto);

       produto.setId(produto.getId());

       // Retornando um produtoDTO atualizado.
        return produtoDTO;
    }

    /**
     * Método para deletar um produto pelo Id.
     * 
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        // Verificar se o produto exite
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não existir lança uma exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id:"+id+ " produto não encontrado");
        }
        // Deleta o produto pelo id.
        produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar o Produto na lista.
     * 
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO) {
       // Passar o Id para o ProdutoDTO
        produtoDTO.setId(id);

       // Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

       // Converter um produtoDTO em um Produto.
       Produto produto = mapper.map(produtoDTO, Produto.class);

       // Atualiar o produto no Banco de  dados.
        produtoRepository.save(produto);
        
        // Retorna o produtoDTO atualizado
        return produtoDTO;

    }

}
