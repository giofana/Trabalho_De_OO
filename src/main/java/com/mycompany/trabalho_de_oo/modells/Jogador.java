package com.mycompany.trabalho_de_oo.modells;
public class Jogador {
    private String nome;
    private Personagem personagem;

    public Jogador(String nome, Personagem personagem) {
        this.nome = nome;
        this.personagem = personagem;
    }

    public String getNome() { return nome; }
    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
}