package src.modelos;
//Classe suporte para representar o nível de um curso.

public class Nivel {
    private String nomeNivel;
    private int dificuldade;

    public Nivel(String nomeNivel, int dificuldade) {
        this.nomeNivel = nomeNivel;
        this.dificuldade = dificuldade;
    }

    //getters e setters

    public String getNomeNivel() {
        return nomeNivel;
    }
    public void setNomeNivel(String nomeNivel) {
        this.nomeNivel = nomeNivel;
    }
    public Integer getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}
