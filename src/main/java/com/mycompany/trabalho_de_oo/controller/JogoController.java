package com.mycompany.trabalho_de_oo.controller;

import com.mycompany.trabalho_de_oo.modells.*;

public class JogoController {

    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private boolean jogoEmAndamento;
    private Jogador jogadorAtual;

    public JogoController() {
        this.tabuleiro = new Tabuleiro();
        this.jogoEmAndamento = false;
    }

    public void iniciarJogo(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogoEmAndamento = true;
        this.jogadorAtual = jogador1;

        posicionarPersonagensAleatoriamente();
    }

    private void posicionarPersonagensAleatoriamente() {
        int x1, y1, x2, y2;
        do {
            x1 = (int) (Math.random() * 10);
            y1 = (int) (Math.random() * 10);
        } while (!tabuleiro.posicaoValida(x1, y1));

        tabuleiro.posicionarPersonagem(jogador1.getPersonagem(), x1, y1);

        do {
            x2 = (int) (Math.random() * 10);
            y2 = (int) (Math.random() * 10);
        } while (!tabuleiro.posicaoValida(x2, y2) || (x2 == x1 && y2 == y1));

        tabuleiro.posicionarPersonagem(jogador2.getPersonagem(), x2, y2);
        tabuleiro.imprimirTabuleiro(jogador1.getPersonagem(), jogador2.getPersonagem());

        System.out.println("O jogo comecou! " + jogador1.getNome() + " vs " + jogador2.getNome());
        System.out.println("Personagens posicionados no tabuleiro.");

        System.out.println("Turno de " + jogadorAtual.getNome() + ". Aja com sabedoria!");

        System.out.println("Pressione 'Enter' para iniciar o turno.");
        try {
            System.in.read();
            System.in.skip(System.in.available());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isJogoEmAndamento() {
        return jogoEmAndamento;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public Jogador getOponente() {
        return jogadorAtual == jogador1 ? jogador2 : jogador1;
    }

    public void processarAcao(String acao) {
        Personagem personagemAtual = jogadorAtual.getPersonagem();
        Personagem oponente = getOponente().getPersonagem();

        switch (acao.toUpperCase()) {
            case "MOVER":
                if (jogadorAtual instanceof Bot) {
                    int botX = personagemAtual.getPosicaoX();
                    int botY = personagemAtual.getPosicaoY();
                    int oponenteX = oponente.getPosicaoX();
                    int oponenteY = oponente.getPosicaoY();

                    int deltaX = Integer.compare(oponenteX - botX, 0);
                    int deltaY = Integer.compare(oponenteY - botY, 0);

                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        deltaY = 0;
                    } else {
                        deltaX = 0;
                    }

                    if (tabuleiro.posicaoValida(personagemAtual.getPosicaoX() + deltaX, personagemAtual.getPosicaoY() + deltaY)) {
                        tabuleiro.moverPersonagem(personagemAtual, personagemAtual.getPosicaoX() + deltaX, personagemAtual.getPosicaoY() + deltaY);
                    } else {
                        System.out.println("Movimento invalido. Tente novamente.");
                    }
                    tabuleiro.imprimirTabuleiro(jogador1.getPersonagem(), jogador2.getPersonagem());
                    System.out.println(personagemAtual.getNome() + " se moveu para a posicao: (" + personagemAtual.getPosicaoX() + ", " + personagemAtual.getPosicaoY() + ")");
                }
                break;
            case "ATACAR":
                if (tabuleiro.calcularDistancia(personagemAtual, oponente) <= personagemAtual.getAlcanceDeAtaque()) {
                    personagemAtual.atacar(oponente);
                    verificarFimDeJogo();
                } else {
                    System.out.println("O oponente esta fora do alcance de ataque!");
                }
                break;
            case "DEFENDER":
                personagemAtual.defender();
                break;
            case "PODER":
                personagemAtual.usarPoderEspecial(oponente);
                verificarFimDeJogo();
                break;
        }

        if (jogoEmAndamento) {
            if (jogadorAtual instanceof Bot) {
                System.out.println("Pressione 'Enter' para finalizar o turno do Bot.");
            } else {
                System.out.println("Pressione 'Enter' para finalizar o seu turno.");
            }
            try {
                System.in.read();
                System.in.skip(System.in.available());
            } catch (Exception e) {
                e.printStackTrace();
            }
            alternarTurno();
        }
    }

    private void alternarTurno() {
        jogadorAtual = jogadorAtual == jogador1 ? jogador2 : jogador1;
    }

    private void verificarFimDeJogo() {
        if (jogador1.getPersonagem().getPontosDeVida() <= 0
                || jogador2.getPersonagem().getPontosDeVida() <= 0) {
            jogoEmAndamento = false;
        }
    }

    public Jogador determinarVencedor() {
        if (jogador1.getPersonagem().getPontosDeVida() > 0) {
            return jogador1;
        } else {
            return jogador2;
        }
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }
}
