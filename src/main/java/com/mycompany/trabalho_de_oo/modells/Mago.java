package com.mycompany.trabalho_de_oo.modells;
public class Mago extends Personagem {
    public Mago(String nome){
        super(nome, 10, 7, 3);
    }

    @Override
    public void usarPoderEspecial(Personagem oponente){
        int vidaOponente = oponente.getPontosDeVida();
        oponente.setPontosDeVida(this.getPontosDeVida());
        this.setPontosDeVida(vidaOponente);
    }
}