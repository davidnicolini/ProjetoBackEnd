package com.github.primeiro_exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.primeiro_exemplo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
