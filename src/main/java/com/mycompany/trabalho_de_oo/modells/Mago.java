package com.mycompany.trabalho_de_oo.modells;
public class Mago extends Personagem {
    public Mago(String nome){
        super(nome, 10, 7, 3);
    }

    @Override
    public void usarPoderEspecial(Personagem oponente){
        int vidaMago = this.getPontosDeVida();
        int vidaOponente = oponente.getPontosDeVida();
        int vidaRestaurada = (vidaMago + vidaOponente) / 2;
        this.setPontosDeVida(vidaRestaurada);
        oponente.setPontosDeVida(vidaRestaurada);
    }
}