package com.mycompany.trabalho_de_oo;

import java.util.Scanner;

import com.mycompany.trabalho_de_oo.View.JogoView;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando o jogo...");
        JogoView jogoView = new JogoView();
        jogoView.exibirBoasVindas();
        jogoView.configurarJogo();
        jogoView.executarJogo();
    }
}