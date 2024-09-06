package com.example.dbbancopicpay.services;

import com.example.dbbancopicpay.models.Cliente;
import com.example.dbbancopicpay.repositorys.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarClientes(){
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));
    }

    public Cliente excluirCliente(String cpf){
        Cliente cliente = buscarPorCpf(cpf);
        clienteRepository.delete(cliente);

        return cliente;
    }

    public List<Cliente> buscarPorNome(String nome){
        return clienteRepository.findByNomeContainsIgnoreCase(nome);
    }

    public List<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmailContains(email);
    }

    public List<Cliente> buscarPorTelefone(String telefone){
        return clienteRepository.findByTelefone(telefone);
    }

}
