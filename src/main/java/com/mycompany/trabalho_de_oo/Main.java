// Giovana Maieli da Conceição Livramento - 202365172A
// Áurea Cunha Prado - 202365062AC
// Trabalho de OO - Jogo de Turnos


package com.mycompany.trabalho_de_oo;

import com.mycompany.trabalho_de_oo.view.JogoView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o jogo...");
        JogoView jogoView = new JogoView();
        jogoView.exibirBoasVindas();
        jogoView.configurarJogo();
        jogoView.executarJogo();
    }
}