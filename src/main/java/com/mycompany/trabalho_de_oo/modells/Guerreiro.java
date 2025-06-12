package com.mycompany.trabalho_de_oo.modells;
public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 15, 10, 1);
    }

    @Override
    public void usarPoderEspecial(Personagem oponente) {
        this.setForcaDeAtaque(Math.min(30, this.getForcaDeAtaque() * 2));
        System.out.println(this.getNome() + " usou seu poder especial, dobrando sua força de ataque para " + this.getForcaDeAtaque() + ".");
    }
    
}
