/*
 * JoJo's Adventure RPG por Texto
 * Criado por Vinícius Custódio Chelli
 */

// IMPORTS
import java.util.*;

// CLASSES
class Jogador {
    String nome;
    Stand stand;
    int vida = 100;
    int experiencia = 0;
    int nivel = 1;
    int escolhasCorajosas = 0;

    public Jogador(String nome, Stand stand) {
        this.nome = nome;
        this.stand = stand;
    }

    public void ganharExperiencia(int xp) {
        experiencia += xp;
        if (experiencia >= nivel * 50) {
            experiencia = 0;
            nivel++;
            stand.poder += 5;
            vida = Math.min(100, vida + 20);
            System.out.println("\n✨ " + nome + " subiu para o nível " + nivel + "! Seu Stand está mais forte!");
        }
    }
}

class Inimigo {
    String nome;
    int vida;
    int dano;

    public Inimigo(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }
}

class Stand {
    String nome;
    String habilidade;
    String descricao;
    String standCry;
    int poder;

    public Stand(String nome, String habilidade, String descricao, String standCry, int poder) {
        this.nome = nome;
        this.habilidade = habilidade;
        this.descricao = descricao;
        this.standCry = standCry;
        this.poder = poder;
    }

    public void usarHabilidade(Inimigo inimigo) {
        int dano = poder + new Random().nextInt(15);
        inimigo.vida = Math.max(0, inimigo.vida - dano);
        System.out.println(nome + " usou " + habilidade + " e causou " + dano + " de dano a " + inimigo.nome);
    }
}

public class JojoTextRPG {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println(" BEM-VINDO AO JOJO TEXT RPG ");
        System.out.println("==============================");

        System.out.print("Digite o nome do seu personagem: ");
        String nome = sc.nextLine();

        Stand[] standsPorParte = {
            new Stand("Hamon", "Onda de Energia", "Arte marcial com energia vital.", "SEIYA!", 15),
            new Stand("Hamon Supremo", "Luz Solar", "Reflete luz solar em ataques.", "YEAHH!", 20),
            new Stand("Star Platinum", "Parar o Tempo", "Congela o tempo por alguns segundos.", "ORA ORA ORA!", 25),
            new Stand("Crazy Diamond", "Restauração", "Restaura objetos ou pessoas.", "DORARARARA!", 26),
            new Stand("Gold Experience", "Criação de Vida", "Cria vida a partir de matéria inerte.", "MUDA MUDA MUDA!", 28),
            new Stand("Stone Free", "Transformação em Corda", "Transforma o corpo em cordas para ataque e defesa.", "ORA ORA!", 29),
            new Stand("Tusk ACT4", "Spin Infinito", "Gira infinitamente para romper barreiras dimensionais.", "CHUMIMI~IN!", 32),
            new Stand("Wonder of U", "Calamidade Universal", "Causa desastres inevitáveis a quem persegue.", "...", 35)
        };

        String[] partes = {
            "Parte 1 - Phantom Blood", "Parte 2 - Battle Tendency", "Parte 3 - Stardust Crusaders",
            "Parte 4 - Diamond is Unbreakable", "Parte 5 - Golden Wind", "Parte 6 - Stone Ocean",
            "Parte 7 - Steel Ball Run", "Parte 8 - JoJolion"
        };

        String[] eventosNarrativos = {
            "\nVocê vê uma máscara de pedra brilhar sob a lua... a origem do mal começa.",
            "\nTrens velozes e nazistas misteriosos! A corrida pela Super Aja começa.",
            "\nJotaro encara o destino de sua mãe e parte rumo ao Egito com seus aliados.",
            "\nA cidade de Morioh esconde segredos e um serial killer de aparência tranquila.",
            "\nO filho de DIO luta para se tornar digno com a ajuda de seus aliados mafiosos.",
            "\nUma prisão, uma herdeira e o plano de alcançar o paraíso.",
            "\nCorridas de cavalos e política se misturam com Stands e giros infinitos.",
            "\nMemórias, identidades trocadas e um mistério genético na cidade de Morioh."
        };

        Inimigo[] inimigos = {
            new Inimigo("Dio Brando", 60, 12),
            new Inimigo("Kars", 70, 13),
            new Inimigo("DIO", 90, 18),
            new Inimigo("Yoshikage Kira", 100, 20),
            new Inimigo("Diavolo", 120, 22),
            new Inimigo("Enrico Pucci", 140, 24),
            new Inimigo("Funny Valentine", 160, 25),
            new Inimigo("Tooru", 180, 27)
        };

        System.out.println("Escolha sua parte inicial:");
        for (int i = 0; i < partes.length; i++) {
            System.out.println((i + 1) + ". " + partes[i]);
        }
        System.out.print("Opção: ");
        int parteInicial = Integer.parseInt(sc.nextLine()) - 1;

        Stand standEscolhido = standsPorParte[parteInicial];
        Jogador jogador = new Jogador(nome, standEscolhido);

        System.out.println("\n✨ " + nome + ", seu Stand é " + standEscolhido.nome + "!");
        System.out.println("⚔ " + standEscolhido.habilidade + ": " + standEscolhido.descricao);

        for (int i = parteInicial; i < partes.length; i++) {
            if (jogador.vida > 0) {
                System.out.println("\n==============================");
                System.out.println(partes[i]);
                System.out.println(eventosNarrativos[i]);

                System.out.println("Você encontra um desvio no caminho. O que deseja fazer?");
                System.out.println("1. Seguir com cautela pelo caminho escuro.");
                System.out.println("2. Avançar rapidamente e enfrentar qualquer perigo de frente.");
                System.out.print("Escolha: ");
                int decisao = Integer.parseInt(sc.nextLine());
                if (decisao == 1) {
                    System.out.println("Você encontrou um item de cura e recuperou 15 de vida!");
                    jogador.vida = Math.min(100, jogador.vida + 15);
                } else {
                    System.out.println("Você se deparou com um emboscador! Vida reduzida em 10!");
                    jogador.vida = Math.max(0, jogador.vida - 10);
                    jogador.escolhasCorajosas++;
                }

                System.out.println("Inimigo: " + inimigos[i].nome);
                jogador.stand = standsPorParte[i];
                System.out.println("Seu Stand evoluiu para: " + jogador.stand.nome + "!");
                batalha(jogador, inimigos[i]);
                if (jogador.vida > 0) {
                    jogador.ganharExperiencia(30 + i * 10);
                }
            } else {
                break;
            }
        }

        if (jogador.vida > 0) {
            System.out.println("\n✨ Parabéns " + jogador.nome + "! Você concluiu todas as partes e provou ser o JoJo definitivo!");
            if (jogador.escolhasCorajosas >= 4) {
                System.out.println("\n💥 Final Corajoso: Você enfrentou todos os perigos de frente, como um verdadeiro Joestar!");
            } else {
                System.out.println("\n🌟 Final Estratégico: Você sobreviveu com inteligência e prudência, como um verdadeiro gênio!");
            }
        } else {
            System.out.println("\n💀 Final Trágico: " + jogador.nome + " foi derrotado pelas forças do destino...");
        }
    }

    public static void batalha(Jogador jogador, Inimigo inimigo) {
        int turno = 1;
        Random rand = new Random();

        while (jogador.vida > 0 && inimigo.vida > 0) {
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("Nível: " + jogador.nivel + " | XP: " + jogador.experiencia);
            System.out.println("Sua vida: " + jogador.vida + " | Vida do inimigo: " + inimigo.vida);
            System.out.println("1. Ataque com Stand");
            System.out.println("2. Defender (Reduz dano)");
            System.out.print("Escolha: ");
            int escolha = Integer.parseInt(sc.nextLine());

            if (escolha == 1) {
                jogador.stand.usarHabilidade(inimigo);
            } else {
                System.out.println(jogador.nome + " se defendeu! Reduzindo o dano recebido neste turno.");
            }

            if (inimigo.vida > 0) {
                int dano = inimigo.dano;
                if (escolha == 2) dano /= 2;
                jogador.vida = Math.max(0, jogador.vida - dano);
                System.out.println(inimigo.nome + " atacou causando " + dano + " de dano!");
            }

            turno++;
        }

        if (jogador.vida > 0)
            System.out.println("\n" + jogador.nome + " venceu com seu Stand: " + jogador.stand.standCry);
        else
            System.out.println("\n" + jogador.nome + " foi derrotado... seu Stand desaparece na poeira...");
    }
}
