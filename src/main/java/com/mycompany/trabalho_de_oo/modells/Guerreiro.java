package com.mycompany.trabalho_de_oo.modells;
public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 15, 10, 1);
    }

    @Override
    public void usarPoderEspecial(Personagem oponente) {
        this.setForcaDeAtaque(this.getForcaDeAtaque() * 2);
    }
    
}
