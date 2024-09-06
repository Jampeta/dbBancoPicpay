package com.example.dbbancopicpay.repositorys;

import com.example.dbbancopicpay.models.Cliente;
import com.example.dbbancopicpay.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String>{
}
