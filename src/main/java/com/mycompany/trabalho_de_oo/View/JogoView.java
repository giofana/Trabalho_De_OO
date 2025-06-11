package com.mycompany.trabalho_de_oo.View;
import java.util.Scanner;

import com.mycompany.trabalho_de_oo.controller.JogoController;
import com.mycompany.trabalho_de_oo.modells.*;


public class JogoView {
    private Scanner scanner;
    private JogoController controller;

    public JogoView() {
        this.scanner = new Scanner(System.in);
        this.controller = new JogoController();
    }

    public void exibirBoasVindas() {
        System.out.println("=== Duelo de Personagens ===");
        System.out.println("Bem-vindo ao jogo de estrategia por turnos!");
        System.out.println();
    }

    public void configurarJogo() {
        int modo = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Selecione o modo de jogo:");
            System.out.println("1. Dois jogadores humanos");
            System.out.println("2. Jogador humano vs Bot");

            String entrada = scanner.nextLine();

            try {
                modo = Integer.parseInt(entrada);
                if (modo == 1 || modo == 2) {
                    entradaValida = true;
                } else {
                    System.out.println("Opcao invalida. Digite 1 ou 2.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Por favor, digite um numero (1 ou 2).\n");
            }
        }

        System.out.println("Configuracao do Jogador 1:");
        Jogador jogador1 = criarJogador(1);

        Jogador jogador2;
        if (modo == 1) {
            System.out.println("Configuracao do Jogador 2:");
            jogador2 = criarJogador(2);
        } else {
            jogador2 = criarBot();
        }

        controller.iniciarJogo(jogador1, jogador2);
    }

    private Jogador criarJogador(int numero) {
        System.out.print("Nome do jogador " + numero + ": ");
        String nomeJogador = scanner.nextLine();
        boolean tipoValido = false;
        int tipo = 0;

        while(!tipoValido) {
            System.out.println("Selecione o tipo de personagem:");
            System.out.println("1. Arqueiro");
            System.out.println("2. Guerreiro");
            System.out.println("3. Mago");
            String entrada = scanner.nextLine();
            try {
                tipo = Integer.parseInt(entrada);
                if (tipo == 1 || tipo == 2 || tipo == 3) {
                    tipoValido = true;
                } else {
                    System.out.println("Opcao invalida. Digite 1, 2 ou 3.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Por favor, digite um numero (1, 2 ou 3).\n");
            }
        }

        System.out.print("Nome do personagem: ");
        String nomePersonagem = scanner.nextLine();

        Personagem personagem;
        switch (tipo) {
            case 1:
                personagem = new Arqueiro(nomePersonagem);
                break;
            case 2:
                personagem = new Guerreiro(nomePersonagem);
                break;
            case 3:
                personagem = new Mago(nomePersonagem);
                break;
            default:
                personagem = new Guerreiro(nomePersonagem);
        }

        return new Jogador(nomeJogador, personagem);
    }

    private Jogador criarBot() {
        String[] nomesBot = {"Bot Arqueiro", "Bot Guerreiro", "Bot Mago"};
        int tipo = (int) (Math.random() * 3) + 1;
        
        Personagem personagem;
        String nomePersonagem;
        
        switch (tipo) {
            case 1:
                nomePersonagem = "Arqueiro Automatico";
                personagem = new Arqueiro(nomePersonagem);
                break;
            case 2:
                nomePersonagem = "Guerreiro Automatico";
                personagem = new Guerreiro(nomePersonagem);
                break;
            case 3:
                nomePersonagem = "Mago Automatico";
                personagem = new Mago(nomePersonagem);
                break;
            default:
                nomePersonagem = "Bot Generico";
                personagem = new Guerreiro(nomePersonagem);
        }
        
        return new Bot(nomesBot[tipo-1], personagem);
    }

    public void executarJogo() {
        while (controller.isJogoEmAndamento()) {
            exibirEstadoAtual();
            
            if (controller.getJogadorAtual() instanceof Bot) {
                processarTurnoBot();
            } else {
                processarTurnoJogador();
            }
        }
        
        exibirResultadoFinal();
    }

    private void exibirEstadoAtual() {
        System.out.println("\n ============== Estado Atual ==============");
        System.out.println("Tabuleiro:");
        controller.getTabuleiro().imprimirTabuleiro(controller.getJogador1().getPersonagem(), controller.getJogador2().getPersonagem());
        
        System.out.println("\nJogador Atual: " + controller.getJogadorAtual().getNome());
        exibirStatusPersonagem(controller.getJogadorAtual().getPersonagem());
        System.out.println("Oponente: " + controller.getOponente().getNome());
        exibirStatusPersonagem(controller.getOponente().getPersonagem());
    }

    private void exibirStatusPersonagem(Personagem personagem) {
        System.out.println("  " + personagem.getNome() + 
                         " (PV: " + personagem.getPontosDeVida() + 
                         ", Ataque: " + personagem.getForcaDeAtaque() + 
                         ", Defesa: " + personagem.getForcaDeDefesa() + 
                         ", Alcance: " + personagem.getAlcanceDeAtaque() + ")");
    }

    private void processarTurnoJogador() {
        System.out.println("\nEscolha sua acao:");
        System.out.println("1. Mover");
        System.out.println("2. Atacar");
        System.out.println("3. Defender");
        System.out.println("4. Usar Poder Especial");
        System.out.println("5. Sair");
        
        int escolha = scanner.nextInt();
        scanner.nextLine();
        
        switch (escolha) {
            case 1:
                System.out.println("Direcao (C - Cima, B - Baixo, E - Esquerda, D - Direita):");
                String direcao = scanner.nextLine().toUpperCase();
                int deltaX = 0, deltaY = 0;
                switch (direcao) {
                    case "C":
                        deltaY = -1;
                        break;
                    case "B":
                        deltaY = 1;
                        break;
                    case "E":
                        deltaX = -1;
                        break;
                    case "D":
                        deltaX = 1;
                        break;
                    default:
                        System.out.println("Direcao invalida!");
                        return;
                }
                boolean moveu = controller.getJogadorAtual().getPersonagem().mover(deltaX, deltaY, controller.getTabuleiro());
                if(moveu)
                    controller.processarAcao("MOVER");
                else {
                    System.out.println("Movimento invalido. Tente novamente.");
                    solicitarNovaAcao();
                }
                break;
            case 2:
                controller.processarAcao("ATACAR");
                break;
            case 3:
                controller.processarAcao("DEFENDER");
                break;
            case 4:
                controller.processarAcao("PODER");
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    public void solicitarNovaAcao() {
        processarTurnoJogador();
    }


    private void processarTurnoBot() {
        Bot bot = (Bot) controller.getJogadorAtual();
        String acao = bot.decidirAcao(controller.getOponente().getPersonagem(), controller.getTabuleiro());
        System.out.println("\n" + bot.getNome() + " escolheu: " + acao);
        
        controller.processarAcao(acao);
    }

    private void exibirResultadoFinal() {
        Jogador vencedor = controller.determinarVencedor();
        System.out.println("\n=== FIM DE JOGO ===");
        System.out.println("O vencedor e: " + vencedor.getNome() + 
                         " com " + vencedor.getPersonagem().getNome());
        System.out.println("Parabens!");
        
        System.out.println("\nDeseja jogar novamente? (S/N)");
        String resposta = scanner.nextLine().toUpperCase();
        if (resposta.equals("S")) {
            reiniciarJogo();
        }
    }

    private void reiniciarJogo() {
        configurarJogo();
        executarJogo();
    }
}