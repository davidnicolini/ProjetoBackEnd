package com.github.primeiro_exemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.primeiro_exemplo.services.ProdutoService;
import com.github.primeiro_exemplo.shared.ProdutoDTO;
import com.github.primeiro_exemplo.view.model.ProdutoResponse;
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
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        List<ProdutoDTO> produtos = produtoService.obterTodos();
        
        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream()
        .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class)).collect(Collectors.toList());
              
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable Integer id) {
        //try{

        Optional <ProdutoDTO> dto = produtoService.obterPorId(id);

        ProdutoResponse produto = new ModelMapper().map(dto.get(),ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(produtoDTO), HttpStatus.OK);

        //} catch (Exception e){
         //   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //}
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        // Poderia ter uma regra de negocios aqui para validar o produto;
        return produtoService.adicionar(produto);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        // Aqui poderia ter uma logica
        produtoService.deletar(id);
        return "Produto com o id: " + id + ", foi deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        // Ter uma validação do Id
        produto.setId(id);
        return produtoService.atualizar(id, produto);
    }
}
