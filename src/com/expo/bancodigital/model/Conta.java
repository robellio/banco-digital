package com.expo.bancodigital.model;

import com.expo.bancodigital.utils.IConta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1023;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<Transacao> transacoes = new ArrayList<>();
    protected double saqueDiario;
    protected LocalDate ultimoDiaSaque;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saqueDiario = 0;
        this.ultimoDiaSaque = LocalDate.now();
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido!");
        }

        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }


        LocalDate hoje = LocalDate.now();
        if (!hoje.isEqual(ultimoDiaSaque)) {
            saqueDiario = 0;
            ultimoDiaSaque = hoje;
        }

        if (saqueDiario + valor > 1000) {
            throw new IllegalArgumentException("Limite diário de saque excedido.");
        }

        saldo -= valor;
        saqueDiario += valor;
        transacoes.add(new Transacao(LocalDateTime.now(), 'S', valor));
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito inválido.");
        }

        saldo += valor;
        transacoes.add(new Transacao(LocalDateTime.now(),'D', valor));
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
        transacoes.add(new Transacao(LocalDateTime.now(), 'T', valor));
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println("\nHistórico de transações:");

        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }
}
