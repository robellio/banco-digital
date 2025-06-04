package com.expo.bancodigital;

import com.expo.bancodigital.model.*;

import java.util.Scanner;

public class BancoDigital {

    private static Banco banco = new Banco();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        banco.setNome("Banco Digital");
        int opcao;

        do {
            System.out.println("\n==== " + banco.getNome().toUpperCase() + " ====");
            System.out.println("1. Criar conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Extrato");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    consultarSaldo();
                    break;
                case 7:
                    System.out.println("Obrigado por utilizar nosso banco!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void criarConta() {
        System.out.println("\n==== Criar Conta ====");
        System.out.print("Nome do titular: ");
        String nomeTitular = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nomeTitular);

        System.out.println("Escolha o Tipo de Conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();

        Conta conta;

        if (tipo == 1) {
            conta = new ContaCorrente(cliente);
        } else {
            conta = new ContaPoupanca(cliente);
        }
    }

}
