package com.github.primeiro_exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.primeiro_exemplo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
