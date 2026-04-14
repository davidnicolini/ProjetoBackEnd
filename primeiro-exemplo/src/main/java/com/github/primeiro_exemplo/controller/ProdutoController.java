package com.github.primeiro_exemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.primeiro_exemplo.services.ProdutoService;
import com.github.primeiro_exemplo.model.Produto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; 

    @GetMapping
    public List<Produto> obterTodos(){
        return produtoService.obterTosdos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable Integer id){
       return produtoService.obterPorId(id);
    }

   @PostMapping
   public Produto adicionar(@RequestBody Produto produto){
        //Poderia ter uma regra de negocios aqui para validar o produto;
        return produtoService.adicionar(produto);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id){
        //Aqui poderia ter uma logica 
        produtoService.deletar(id);
        return "Produto com o id: " + id + ", foi deletado com sucesso!";
    }
      
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto){
        //Ter uma validação do Id
        produto.setId(id);
        return produtoService.atualizar(id, produto);
    }
}
