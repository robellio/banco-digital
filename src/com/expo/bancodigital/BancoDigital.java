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

        banco.getContas().add(conta);
        System.out.println("\nConta Criada com Sucesso!");
        System.out.println("Agência: " + conta.getAgencia());
        System.out.println("Número: " + conta.getNumero());
    }

    private static Conta encontrarConta(int agencia, int numero) {
        for (Conta conta : banco.getContas()) {
            if (conta.getAgencia() == agencia && conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    private static void depositar() {
        System.out.println("\n==== Depósito ====");
        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = encontrarConta(agencia, numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();

        conta.depositar(valor);
        System.out.println("Depósito realizado com Sucesso!");
        System.out.println("Novo saldo: " + conta.getSaldo());

    }

    private static void sacar() {
        System.out.println("\n==== Saque ====");
        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número da Conta: ");
        int numero = scanner.nextInt();

        Conta conta = encontrarConta(agencia, numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.println("Valor do saque: ");
        double valor = scanner.nextDouble();

        try {
            conta.sacar(valor);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transferir() {
        System.out.println("\n==== Transferência ====");
        System.out.println("Conta de Origem:");
        System.out.print("Agência: ");
        int agenciaOrigem = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número: ");
        int numeroOrigem = scanner.nextInt();
        scanner.nextLine();

        Conta origem = encontrarConta(agenciaOrigem, numeroOrigem);
        if (origem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        System.out.println("Conta de destino");
        System.out.print("Agência: ");
        int agenciaDestino = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número: ");
        int numeroDestino = scanner.nextInt();
        scanner.nextLine();

        Conta destino = encontrarConta(agenciaDestino, numeroDestino);

        if (destino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }

        System.out.println("Valor da transferência: ");
        double valor = scanner.nextDouble();

        try {
            origem.transferir(valor,destino);
            System.out.println("Transferência realizada com Sucesso.");
            System.out.println("Novo saldo: " + origem.getSaldo());
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarSaldo() {
        System.out.println("\n==== Consultar Saldo ====");
        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();

        Conta conta = encontrarConta(agencia, numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
        }

        System.out.println("Saldo atual: " + conta.getSaldo());
    }

    private static void imprimirExtrato() {
        System.out.println("\n==== Extrato ====");
        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = encontrarConta(agencia, numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        conta.imprimirExtrato();

    }

}
