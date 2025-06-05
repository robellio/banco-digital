package com.expo.bancodigital.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private LocalDateTime data;
    private char tipo;
    private double valor;

    public Transacao(LocalDateTime data, char tipo, double valor) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String tipoStr = "";
        switch (tipo) {
            case 'D':
                tipoStr = "Depósito";
            break;
            case 'S':
                tipoStr = "Saque";
            break;
            case 'T':
                tipoStr = "Transferência";
            break;
        }
        return String.format("[%s] %s: R$ %.2f", data, tipoStr, valor);
    }
}
