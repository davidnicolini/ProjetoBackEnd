package com.github.primeiro_exemplo.view.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.primeiro_exemplo.services.ProdutoService;
import com.github.primeiro_exemplo.shared.ProdutoDTO;
import com.github.primeiro_exemplo.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ModelMapper mapper = new ModelMapper();

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodosProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos();
        
        List<ProdutoResponse> resposta = produtos.stream()
            .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
            .collect(Collectors.toList());
              
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> obterProdutoPorId(@PathVariable Integer id) {
        return produtoService.obterProdutoPorId(id)
            .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionarProduto(@RequestBody ProdutoDTO produtoDto) {
       
        ProdutoDTO produtoDTOCadastrado = produtoService.adicionarProduto(produtoDto);
        ProdutoResponse resposta = mapper.map(produtoDTOCadastrado, ProdutoResponse.class);
        
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>("Produto com o id: " + id + ", foi deletado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDto) {
        ProdutoDTO dtoAtualizado = produtoService.atualizarProduto(id, produtoDto);
        ProdutoResponse resposta = mapper.map(dtoAtualizado, ProdutoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
