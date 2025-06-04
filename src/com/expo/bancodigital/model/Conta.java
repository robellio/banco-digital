package com.expo.bancodigital.model;

import com.expo.bancodigital.utils.IConta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1023;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<Transacao> transacoes new ArrayList<>();
    protected double saqueDiario;
    protected Date ultimoDiaSaque;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saqueDiario = 0;
        this.ultimoDiaSaque = new Date();

    }

}
