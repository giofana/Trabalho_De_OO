package com.mycompany.trabalho_de_oo;

import java.util.Scanner;

import com.mycompany.trabalho_de_oo.View.JogoView;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando o jogo...");
        boolean entradaValida = false;
        int modo = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!entradaValida) {
                System.out.println("Selecione o modo de jogo:");
                System.out.println("1. Modo Classico");
                System.out.println("2. Modo Avancado");

                String entrada = scanner.nextLine();

                try {
                    modo = Integer.parseInt(entrada);
                    if (modo == 1 || modo == 2) {
                        entradaValida = true;
                    } else {
                        System.out.println("Opcao invalida. Digite 1 ou 2.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Por favor, digite um numero (1 ou 2).\n");
                }
            }

            if (modo == 1) {
                JogoView jogoView = new JogoView();
                jogoView.exibirBoasVindas();
                jogoView.configurarJogo();
                jogoView.executarJogo();
            } else {
                System.out.println("Modo de jogo inválido.");
            }
        }
    }
}