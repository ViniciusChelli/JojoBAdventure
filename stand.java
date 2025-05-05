import java.util.*;

/**
 * Classe que representa um Stand no sistema de batalha.
 * Cada Stand possui atributos de combate e uma habilidade especial.
 */
class Stand {
    String nome, habilidade, descricao, standCry;
    int poderDestrutivo, velocidade, alcance, persistencia, precisao, potencial, vida;
    
    /**
     * Construtor da classe Stand.
     */
    public Stand(String nome, String poderDestrutivo, String velocidade, String alcance, 
                 String persistencia, String precisao, String potencial, String habilidade, 
                 String descricao, String standCry) {
        this.nome = nome;
        this.poderDestrutivo = converterClassificacao(poderDestrutivo);
        this.velocidade = converterClassificacao(velocidade);
        this.alcance = converterClassificacao(alcance);
        this.persistencia = converterClassificacao(persistencia);
        this.precisao = converterClassificacao(precisao);
        this.potencial = converterClassificacao(potencial);
        this.habilidade = habilidade;
        this.descricao = descricao;
        this.standCry = standCry;
        this.vida = 100;
    }
    
    /**
     * Converte a classificação de atributo de letra para valor numérico.
     */
    private int converterClassificacao(String classe) {
        Map<String, Integer> valores = Map.of("A", 100, "B", 80, "C", 60, "D", 40, "E", 20);
        return valores.getOrDefault(classe, 0);
    }
    
    /**
     * Método para um Stand atacar outro Stand.
     */
    public String atacar(Stand alvo) {
        int dano = Math.max(10, this.poderDestrutivo - (alvo.persistencia / 2));
        alvo.vida = Math.max(0, alvo.vida - dano);
        return this.nome + " ataca " + alvo.nome + " causando " + dano + " de dano!";
    }
    
    /**
     * Método para um Stand usar sua habilidade especial em outro Stand.
     */
    public String usarHabilidade(Stand alvo) {
        if (this.nome.equals("Star Platinum")) {
            alvo.vida = Math.max(0, alvo.vida - 60);
            return this.nome + " usou PARAR O TEMPO! " + alvo.nome + " recebeu 60 de dano!";
        } else if (this.nome.equals("The World")) {
            alvo.vida = Math.max(0, alvo.vida - 55);
            return this.nome + " usou ZA WARUDO! " + alvo.nome + " recebeu 55 de dano!";
        } else if (this.nome.equals("Crazy Diamond")) {
            this.vida = Math.min(100, this.vida + 40);
            return this.nome + " usou RESTAURAÇÃO! Recuperou 40 de vida!";
        }
        return this.nome + " tentou usar sua habilidade, mas nada aconteceu.";
    }
}

/**
 * Classe principal para simulação de duelos entre Stands.
 */
public class StandDuel {
    static Map<String, Stand> bibliotecaStands = new HashMap<>();

    /**
     * Método principal para iniciar a simulação.
     */
    public static void main(String[] args) {
        carregarStands();
        simularDuelo(bibliotecaStands.get("Tusk Act 4"), bibliotecaStands.get("Made in Heaven"));
    }

    /**
     * Carrega os Stands disponíveis para batalha.
     */
    public static void carregarStands() {
        bibliotecaStands.put("Star Platinum", new Stand("Star Platinum", "A", "A", "C", "A", "A", "C", "Parar o Tempo", "Pode congelar o tempo.", "ORA ORA ORA!"));
        bibliotecaStands.put("The World", new Stand("The World", "A", "A", "C", "A", "A", "C", "Za Warudo!", "Congela o tempo para atacar sem ser atingido.", "MUDA MUDA MUDA!"));
        bibliotecaStands.put("Crazy Diamond", new Stand("Crazy Diamond", "A", "B", "C", "A", "A", "B", "Restauração", "Pode curar aliados.", "DORARARA!"));
        bibliotecaStands.put("King Crimson", new Stand("King Crimson", "A", "A", "C", "A", "B", "B", "Apagar o Tempo", "Pode apagar alguns segundos do tempo.", "Epitaph prevê tudo!"));
        bibliotecaStands.put("Gold Experience", new Stand("Gold Experience", "B", "A", "C", "A", "A", "A", "Criação de Vida", "Pode gerar vida a partir de objetos inanimados.", "MUDA MUDA MUDA!"));
        bibliotecaStands.put("Silver Chariot", new Stand("Silver Chariot", "B", "S", "B", "B", "A", "B", "Espadachim Rápido", "Movimentos incrivelmente rápidos.", "HAAAAH!"));
        bibliotecaStands.put("Tusk Act 4", new Stand("Tusk Act 4", "A", "A", "A", "A", "A", "A", "Infinite Rotation", "Rotações infinitas que atravessam dimensões.", "CHUMIMIN~!"));
        bibliotecaStands.put("D4C Love Train", new Stand("D4C Love Train", "A", "A", "A", "A", "A", "A", "Dimensional Hop", "Pode viajar entre dimensões.", "DOUJYAAAN!"));
        bibliotecaStands.put("Weather Report", new Stand("Weather Report", "B", "B", "B", "A", "A", "A", "Controle Climático", "Pode manipular o clima.", "..."));
        bibliotecaStands.put("Made in Heaven", new Stand("Made in Heaven", "A", "A", "A", "A", "A", "EX", "Aceleração do Tempo", "Pode acelerar o tempo.", "SOSHITE TOKI WA UGOKIDASU!"));
    }

    /**
     * Simula um duelo entre dois Stands.
     */
    public static void simularDuelo(Stand stand1, Stand stand2) {
        Random rand = new Random();
        int turno = 1;
        int maxTurnos = 10;

        while (stand1.vida > 0 && stand2.vida > 0 && turno <= maxTurnos) {
            Stand atacante = rand.nextBoolean() ? stand1 : stand2;
            Stand defensor = atacante == stand1 ? stand2 : stand1;

            String resultado;
            if (rand.nextDouble() > 0.6) {
                resultado = atacante.usarHabilidade(defensor);
            } else {
                resultado = atacante.atacar(defensor);
            }

            System.out.println("Turno " + turno + ": " + resultado + " Vida restante: " + defensor.nome + " " + defensor.vida);
            turno++;
        }

        Stand vencedor = (stand1.vida > stand2.vida) ? stand1 : stand2;
        System.out.println("\nBatalha concluída! O vencedor é " + vencedor.nome + " com um " + vencedor.standCry + "!!!\n");
    }
}
