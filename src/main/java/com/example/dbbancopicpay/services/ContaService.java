
package com.example.dbbancopicpay.services;

import com.example.dbbancopicpay.models.Conta;
import com.example.dbbancopicpay.repositorys.ClienteRepository;
import com.example.dbbancopicpay.repositorys.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public List<Conta> buscarContas(){
        return contaRepository.findAll();
    }

    @Transactional
    public Conta salvarConta(Conta conta){
        return contaRepository.save(conta);
    }
}
