package com.example.dbbancopicpay.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Random;

@Entity
public class Conta {

    @Id
    @Column(name = "numero_conta")
    private String idConta;

    @NotNull(message = "O Valor(saldo) não pode ser vazio")
    @Min(value = 0, message = "Saldo tem que ser maior que 0")
    //@Digits(integer = 10, fraction = 2)
    private double saldo;

    @NotNull(message = "O Valor(limite) não pode ser vazio")
    @Column(name = "limite_especial")
    //@Digits(integer = 10, fraction = 2)
    @Min(value = 0, message = "Saldo tem que ser maior que 0")
    private double limite;

    @NotNull(message = "O Valor (CPF) não pode ser vazio")
    @Column(name = "cliente_cpf")
    private String clienteCpf;

    public void Conta(double saldo, double limite, String clienteCpf){
        Random gerador = new Random();
        int numero1 = gerador.nextInt(10);
        int numero2 = gerador.nextInt(10);
        int numero3 = gerador.nextInt(10);
        int numero4 = gerador.nextInt(10);
        int soma = numero1 + numero2 + numero3 + numero4;
        int numero5 = soma % 10;
        String idConta  = Integer.toString(numero1) + Integer.toString(numero2) + Integer.toString(numero3) + Integer.toString(numero4) + Integer.toString(numero5);

        this.idConta = idConta;
        this.saldo = saldo;
        this.limite = limite;
        this.clienteCpf = clienteCpf;


    }
    public String getIdConta() {
        return idConta;
    }
    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }
    public double getLimite() {
        return limite;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getClienteCpf() {
        return clienteCpf;
    }
    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void setLimite(double limite) {
        this.limite = limite;
    }
}
