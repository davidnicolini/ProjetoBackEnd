package com.github.primeiro_exemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.primeiro_exemplo.model.Produto;
import com.github.primeiro_exemplo.model.exception.ResourceNotFoundException;

public class ProdutoRepository_old {

    private final List<Produto> produtos = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Método para Retorna uma lista de Produtos.
     * 
     * @return Lista de produtos.
     */
    public List<Produto> obterTosdos() {
        return produtos;
    }

    /**
     * Método que retorna o produto pelo seu Id.
     * 
     * @param id do produto que será localizado.
     * @return Retona um produto caso seja endontrado.
     */

    public Optional<Produto> obterPorId(Integer id) {
        Optional<Produto> produtoEncontrado = produtos.stream().filter(produto -> Objects.equals(produto.getId(), id))
                .findFirst();

        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não pode ser Encontrado! pois o id: " + id + ", não Exite!");
        }
        return produtoEncontrado;

    }

    /**
     * Método para adicionar produto.
     * 
     * @param produto que será adicionado .
     * @return o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Método para deletar um produto pelo Id.
     * 
     * @param id do produto a ser deletado.
     */

    public void deletar(Integer id) {
        Optional<Produto> produtoEncontrado = obterPorId(id);
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Produto não pode ser Deletado! pois o id: " + id + ", não foi encontrado");
        }
        produtos.removeIf(produto -> Objects.equals(produto.getId(), id));
    }

    /**
     * Método para atualizar o Produto na lista.
     * 
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto) {
        // Encontrar um produto na lista
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não pode ser Atualizado pois não foi encontrado");
        }
        // Eu tenho que remover o produto antigo da lista
        deletar(produto.getId());
        // Depois adicionar o produto atualizado na lista
        produtos.add(produto);
        return produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

}
