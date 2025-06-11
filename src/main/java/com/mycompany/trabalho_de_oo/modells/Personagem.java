package com.mycompany.trabalho_de_oo.modells;

public abstract class Personagem {
    private String nome;
    private int pontosDeVida;
    private int forcaDeAtaque;
    private int forcaDeDefesa;
    private int alcanceDeAtaque;
    private int posicaoX;
    private int posicaoY;
    private final int defesaInicial;

    public Personagem(String nome, int forcaDeAtaque, int forcaDeDefesa, int alcanceDeAtaque) {
        this.nome = nome;
        this.pontosDeVida = 100;
        this.forcaDeAtaque = forcaDeAtaque;
        this.forcaDeDefesa = forcaDeDefesa;
        this.defesaInicial = forcaDeDefesa;
        this.alcanceDeAtaque = alcanceDeAtaque;
    }

    public abstract void usarPoderEspecial(Personagem oponente);

    public void atacar(Personagem oponente){
        int dano = Math.max(0, this.forcaDeAtaque - oponente.getForcaDeDefesa());
        oponente.setPontosDeVida(oponente.getPontosDeVida() - dano);
        System.out.println(this.nome + " atacou " + oponente.getNome() + " causando " + dano + " de dano.");
        System.out.println(oponente.getNome() + " agora tem " + oponente.getPontosDeVida() + " pontos de vida.");
        oponente.setForcaDeDefesa(oponente.getForcaDeDefesa() - this.forcaDeAtaque);
    }

    public void defender(){
        this.forcaDeDefesa = this.defesaInicial;
    }

    public boolean mover(int deltaX, int deltaY, Tabuleiro tabuleiro) {
        int novaX = posicaoX + deltaX;
        int novaY = posicaoY + deltaY;
        
        if(tabuleiro.posicaoValida(novaX, novaY)) {
            System.out.println("Movendo personagem " + nome + " de (" + posicaoX + ", " + posicaoY + ") para (" + novaX + ", " + novaY + ")");
            tabuleiro.moverPersonagem(this, novaX, novaY);
            this.posicaoX = novaX;
            this.posicaoY = novaY;
            return true;
        }
        System.out.println("Movimento inválido para " + nome + " de (" + posicaoX + ", " + posicaoY + ") para (" + novaX + ", " + novaY + ")");
        return false;
    }

    public String getNome() { return nome; }
    public int getPontosDeVida() { return pontosDeVida; }
    public int getForcaDeAtaque() { return forcaDeAtaque; }
    public int getForcaDeDefesa() { return forcaDeDefesa; }
    public int getAlcanceDeAtaque() { return alcanceDeAtaque; }
    public int getPosicaoX() { return posicaoX; }
    public int getPosicaoY() { return posicaoY; }
    public int getDefesaInicial() { return defesaInicial; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPontosDeVida(int pontosDeVida) { this.pontosDeVida = pontosDeVida; }
    public void setForcaDeAtaque(int forcaDeAtaque) { this.forcaDeAtaque = forcaDeAtaque; }
    public void setForcaDeDefesa(int forcaDeDefesa) { this.forcaDeDefesa = forcaDeDefesa; }
    public void setAlcanceDeAtaque(int alcanceDeAtaque) { this.alcanceDeAtaque = alcanceDeAtaque; }
    public void setPosicaoX(int posicaoX) { this.posicaoX = posicaoX; }
    public void setPosicaoY(int posicaoY) { this.posicaoY = posicaoY; }
}