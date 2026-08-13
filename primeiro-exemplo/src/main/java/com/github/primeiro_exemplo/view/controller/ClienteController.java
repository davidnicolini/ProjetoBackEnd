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

import com.github.primeiro_exemplo.services.ClienteService;
import com.github.primeiro_exemplo.shared.ClienteDTO;
import com.github.primeiro_exemplo.view.model.ClienteResponse;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    public final ClienteService clienteService;
    public final ModelMapper mapper = new ModelMapper();
    
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obterTodosClientes() {
        List<ClienteDTO> clientes = clienteService.obterTodosClientes();
        
        List<ClienteResponse> resposta = clientes.stream()
            .map(clienteDTO -> mapper.map(clienteDTO, ClienteResponse.class))
            .collect(Collectors.toList());
              
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

     @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obterClientePorId(@PathVariable Integer id) {
        return clienteService.obterClientePorId(id)
        .map(clienteDTO -> mapper.map(clienteDTO, ClienteResponse.class))
        .map(ResponseEntity::ok)
        .orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Optional<ClienteResponse>> criarCliente(@RequestBody ClienteDTO clienteDto) {
        
        ClienteDTO clienteDTOCadastrado = clienteService.criarCliente(clienteDto);
        ClienteResponse resposta = mapper.map(clienteDTOCadastrado, ClienteResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return new ResponseEntity<>("Cliente com o id: " + id + ", foi deletado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO dtoAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        ClienteResponse resposta = mapper.map(dtoAtualizado, ClienteResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
