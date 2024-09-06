package com.example.dbbancopicpay.controllers;

import com.example.dbbancopicpay.models.Cliente;
import com.example.dbbancopicpay.models.Conta;
import com.example.dbbancopicpay.services.ClienteService;
import com.example.dbbancopicpay.services.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;
    private final ClienteService clienteService;
    private final Validator validador;

    public ContaController(ContaService contaService, ClienteService clienteService, Validator validador) {
        this.contaService = contaService;
        this.clienteService = clienteService;
        this.validador = validador;
    }

    @GetMapping("/buscar")
    public List<Conta> buscarContas(){
        return contaService.buscarContas();
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirConta(@Valid @RequestBody Conta conta, BindingResult resultado){

       String cpf = conta.getClienteCpf();
       if(resultado.hasErrors()){
           Map<String, String> erros = new HashMap<>();
           for (FieldError erro : resultado.getFieldErrors()) {
               erros.put(erro.getField(), erro.getDefaultMessage());
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
       }
       else if(!clienteService.verificarPorCpf(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Um cliente com este cpf não existe");
       }
       else{
           contaService.salvarConta(conta);
           return ResponseEntity.ok("Conta inserida");
       }
    }
}
