package com.github.primeiro_exemplo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.primeiro_exemplo.model.Produto;
import com.github.primeiro_exemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para Retorna uma lista de Produtos.
     * @return Lista de produtos.
     */ 
    public List<Produto> obterTosdos(){
        return produtoRepository.obterTosdos();
    }

    /**
     * Método que retorna o produto pelo seu Id.
     * @param id do produto que será localizado.
     * @return Retona um produto caso seja endontrado.
     */
    public Optional<Produto> obterPorId(Integer id){
       return produtoRepository.obterPorId(id);
    }

    /**
     * Método para adicionar produto.
     * @param produto que será adicionado .
     * @return o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto){
        //Poderia ter uma regra de negocios aqui para validar o produto;
        return produtoRepository.adicionar(produto);
    }

    /**
     * Método para deletar um produto pelo Id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        //Aqui poderia ter uma logica 
        produtoRepository.deletar(id);
    }

    /**
     * Método para atualizar o Produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto){
        //Ter uma validação do Id
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }

}
