/**
 * Stand Maker - Criador de Stands Personalizados
 * Inspirado no universo de JoJo's Bizarre Adventure
 * Desenvolvido por Vinícius Custódio Chelli
 */

import java.util.*;

class CustomStand {
    String nome;
    String habilidade;
    String descricao;
    String standCry;
    Map<String, String> atributos = new HashMap<>();

    public CustomStand(String nome, String habilidade, String descricao, String standCry, Map<String, String> atributos) {
        this.nome = nome;
        this.habilidade = habilidade;
        this.descricao = descricao;
        this.standCry = standCry;
        this.atributos = atributos;
    }

    public void exibirFicha() {
        System.out.println("\n===== STAND CRIADO =====");
        System.out.println("Nome: " + nome);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Descrição: " + descricao);
        System.out.println("Stand Cry: " + standCry);
        System.out.println("Atributos:");
        atributos.forEach((k, v) -> System.out.println("  " + k + ": " + v));
    }
}

public class StandMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Criador de Stand ===");
        System.out.print("Digite o nome do seu Stand: ");
        String nome = scanner.nextLine();

        System.out.print("Qual é a habilidade principal do Stand? ");
        String habilidade = scanner.nextLine();

        System.out.print("Descreva o efeito da habilidade: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o Stand Cry: ");
        String standCry = scanner.nextLine();

        String[] atributos = {"Poder Destrutivo", "Velocidade", "Alcance", "Persistência", "Precisão", "Potencial"};
        Map<String, String> stats = new HashMap<>();

        for (String atributo : atributos) {
            System.out.print("Nível para " + atributo + " (A/B/C/D/E/S): ");
            String valor = scanner.nextLine().toUpperCase();
            stats.put(atributo, valor);
        }

        CustomStand meuStand = new CustomStand(nome, habilidade, descricao, standCry, stats);
        meuStand.exibirFicha();
    }
}
