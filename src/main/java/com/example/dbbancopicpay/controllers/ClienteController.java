package com.example.dbbancopicpay.controllers;

import com.example.dbbancopicpay.models.Cliente;
import com.example.dbbancopicpay.services.ClienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final Validator validador;

    public ClienteController(ClienteService clienteService, Validator validador) {
        this.clienteService = clienteService;
        this.validador = validador;
    }

    @GetMapping("/buscar")
    public List<Cliente> listarClientes(){
        return clienteService.buscarClientes();
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirCliente(@Valid @RequestBody Cliente cliente,
                                                 BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            clienteService.salvarCliente(cliente);
            return ResponseEntity.ok("Cliente inserido com sucesso");
        }
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<String> atualizarProduto( @PathVariable String cpf,
                                                    @Valid @RequestBody Cliente clienteAtualizado,
                                                    BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            Cliente clienteExistence = clienteService.buscarPorCpf(cpf);
            Cliente cliente = clienteExistence;
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            clienteService.salvarCliente(cliente);
            return ResponseEntity.ok("Produto atualizado com sucesso");
        }
    }
    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity<String> excluirCliente(@PathVariable String cpf){
        if(clienteService.buscarPorCpf(cpf) != null) {
            clienteService.excluirCliente(cpf);
            return ResponseEntity.ok("Cliente excluido com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

    @GetMapping("/buscarPorNome/{nome}")
    public List<Cliente> listarPorNome(@PathVariable String nome){
        return clienteService.buscarPorNome(nome);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public List<Cliente> listarPorEmail(@PathVariable String email){
        return clienteService.buscarPorEmail(email);
    }

    @GetMapping("/buscarPorTelefone/{telefone}")
    public List<Cliente> listarPorTelefone(@PathVariable String telefone){
        return clienteService.buscarPorTelefone(telefone);
    }

//    @GetMapping("/buscarPorCpf/{cpf}")
//    public List<Cliente> buscarPorCpf(@PathVariable String cpf){
//        return clienteService.buscarPorCpf(cpf);
//    }
}
