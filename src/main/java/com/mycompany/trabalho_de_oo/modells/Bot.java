package com.mycompany.trabalho_de_oo.modells;

public class Bot extends Jogador {

    public Bot(String nome, Personagem personagem) {
        super(nome, personagem);
    }

    public String decidirAcao(Personagem oponente, Tabuleiro tabuleiro) {
        int distancia = tabuleiro.calcularDistancia(this.getPersonagem(), oponente);
        
        if (distancia <= this.getPersonagem().getAlcanceDeAtaque()) {
            
            if(this.getPersonagem().getPontosDeVida() < 30) {
                return "DEFENDER";
            
            } if(Math.random() < 0.3) {
                return "PODER";
            }
            return "ATACAR";
        } else {
            return "MOVER";
        }
    }
}
