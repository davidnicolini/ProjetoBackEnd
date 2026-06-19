package com.github.primeiro_exemplo.view.controller;

import java.util.List;
import java.util.Optional;
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

    // Instanciar o mapper uma vez ou injetar via Spring como melhor prática
    private final ModelMapper mapper = new ModelMapper();

    ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        List<ProdutoDTO> produtos = produtoService.obterTodos();
        
        List<ProdutoResponse> resposta = produtos.stream()
            .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
            .collect(Collectors.toList());
              
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
        Optional<ProdutoDTO> dto = produtoService.obterPorId(id);

        if (dto.isPresent()) {
            ProdutoResponse produtoResponse = mapper.map(dto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(Optional.of(produtoResponse), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoDTO produtoDto) {
        // O controller recebe o DTO do cliente, envia pro Service processar
        ProdutoDTO dtoCadastrado = produtoService.adicionar(produtoDto);
        
        // Converte o resultado para ProdutoResponse para devolver ao cliente
        ProdutoResponse resposta = mapper.map(dtoCadastrado, ProdutoResponse.class);
        
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
        return new ResponseEntity<>("Produto com o id: " + id + ", foi deletado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDto) {
        // Envia o DTO para o Service atualizar a regra de negócio
        ProdutoDTO dtoAtualizado = produtoService.atualizar(id, produtoDto);
        
        // Converte o DTO atualizado para Response
        ProdutoResponse resposta = mapper.map(dtoAtualizado, ProdutoResponse.class);
        
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
