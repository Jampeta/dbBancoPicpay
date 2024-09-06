package com.example.dbbancopicpay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {

    @Id
    @NotNull(message = "O Valor (CPF) não pode ser vazio")
    private String cpf;

    @NotNull(message = "O Valor (Nome) não pode ser vazio")
    @Size(min = 3, message = "O  nome tem que ter no minimo 3 digitos")
    private String nome;

    @NotNull(message = "O email não pode ser vazio")
    @Email
    private String email;

    @NotNull(message = "O telefone não pode ser vazio")
    @Size(min = 11, max = 15,  message = "O telefome tem que ter no minimo 11 caracteres e  no maximo 15 ")
    private String telefone;

    public void Cliente(String cpf, String nome, String email, String telefone){
        this.cpf= cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
