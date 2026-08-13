package com.github.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.primeiro_exemplo.model.Cliente;
import com.github.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.github.primeiro_exemplo.repository.ClienteRepository;
import com.github.primeiro_exemplo.shared.ClienteDTO;

@Service
public class ClienteService {
    
    public ClienteRepository clienteRepository;
    public ModelMapper mapper = new ModelMapper();
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> obterTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
        .map(cliente -> new ModelMapper()
        .map(cliente, ClienteDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> obterClientePorId(Integer id) {
        clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        Optional<Cliente> cliente = clienteRepository.findById(id);
        ClienteDTO dto = new ModelMapper().map(cliente.get(), ClienteDTO.class);
        return Optional.of(dto);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
         
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        clienteDTO.setId(cliente.getId()); 
        return clienteDTO;
    }

    public void deletarCliente(Integer id) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(clienteExistente);
    }

    public ClienteDTO atualizarCliente(Integer id, ClienteDTO clienteDTO) {
        
        clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        
        clienteDTO.setId(id);
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        clienteRepository.save(cliente);
        return clienteDTO;
    }
    
    
}
