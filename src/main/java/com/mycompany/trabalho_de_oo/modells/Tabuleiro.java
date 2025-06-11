package com.mycompany.trabalho_de_oo.modells;

public class Tabuleiro {

    private static final int TAMANHO = 10;
    private Personagem[][] grade;

    public Tabuleiro() {
        this.grade = new Personagem[TAMANHO][TAMANHO];
    }

    public boolean posicaoValida(int x, int y) {
        return x >= 0 && x < TAMANHO && y >= 0 && y < TAMANHO && grade[x][y] == null;
    }

    public void posicionarPersonagem(Personagem personagem, int x, int y) {
        if (posicaoValida(x, y)) {
            System.out.println("Posicionando " + personagem.getNome() + " na posicao (" + (x + 1) + ", " + (y + 1) + ")");
            personagem.setPosicaoX(x);
            personagem.setPosicaoY(y);
            grade[x][y] = personagem;
        }
    }

    public void removerPersonagem(int x, int y) {
        grade[x][y] = null;
    }

    public void moverPersonagem(Personagem personagem, int novoX, int novoY) {
        int antigoX = personagem.getPosicaoX();
        int antigoY = personagem.getPosicaoY();
        
        if (posicaoValida(novoX, novoY)) {
            removerPersonagem(antigoX, antigoY);
            posicionarPersonagem(personagem, novoX, novoY);
        }
    }

    public int calcularDistancia(Personagem p1, Personagem p2) {
        int dx = Math.abs(p1.getPosicaoX() - p2.getPosicaoX());
        int dy = Math.abs(p1.getPosicaoY() - p2.getPosicaoY());
        return Math.max(dx, dy);
    }

    public void imprimirTabuleiro(Personagem jogador1, Personagem jogador2) {
        
        System.out.print("    ");
        for (int i = 0; i < TAMANHO; i++) {
            System.out.print(i <= 9 ? " " + (i + 1) + "  " : i + "  ");
        }
        System.out.println();

        for (int i = 0; i < TAMANHO; i++) {

            System.out.print("   +");
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print("---" + (j < TAMANHO-1 ? "+" : "+"));
            }
            System.out.println();

            System.out.print(i < 9 ? " " + (i + 1) + " |" : (i + 1) + " |");

            for (int j = 0; j < TAMANHO; j++) {
                if (grade[j][i] == jogador1) {
                    System.out.print(" 1 |"); 
                } else if (grade[j][i] == jogador2) {
                    System.out.print(" 2 |");
                } else {
                    System.out.print("   |"); 
                }
            }
            System.out.println();
        }

        System.out.print("   +");
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print("---" + (j < TAMANHO-1 ? "+" : "+"));
        }
        System.out.println();

        System.out.println("1 - " + jogador1.getNome() + " | 2 - " + jogador2.getNome());
    }
}