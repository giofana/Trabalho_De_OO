package com.mycompany.trabalho_de_oo.modells;
public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 8, 5, 5);
    }
    @Override
    public void usarPoderEspecial(Personagem oponente) {
        this.setAlcanceDeAtaque(this.getAlcanceDeAtaque() + 1);
    }

}
