package com.example.dbbancopicpay.repositorys;

import com.example.dbbancopicpay.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByNomeContainsIgnoreCase(String nome);
    List<Cliente> findByEmailContains(String email);
    List<Cliente> findByTelefone(String telefone);
    List<Cliente> findByCpf(String cpf);
}
