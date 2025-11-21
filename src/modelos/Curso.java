package src.modelos;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Curso {
    private String nome;
    private double mensalidade;
    private List<Nivel> niveis;
    private final long id; 
    private static long idCounter = 1;
   
    // Comparator para ordenar os níveis pela dificuldade
    private Comparator<Nivel> comparatorNivel = new Comparator<>() {
        @Override
        public int compare(Nivel n1, Nivel n2) {
            return n1.getDificuldade().compareTo(n2.getDificuldade());
        }
    };

    // Construtores
    public Curso() {
        this.id = idCounter++;
        this.niveis = new ArrayList<Nivel>();
    }

    public Curso(String nome, double mensalidade, long id) {
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.id = id;
        this.niveis = new ArrayList<Nivel>();
    }

    // Getters
    public String getInfoBase() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public long getId() {
        return id;
    }

    public List<Nivel> getNiveis() {
        return niveis;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void addNivel(Nivel nivel) {
        niveis.add(nivel);
        niveis.sort(comparatorNivel);
    }

    public void removerNivel(Nivel nivel) {
        if (niveis.contains(nivel)) {
            niveis.remove(nivel);
        }
    }

    public int getQuantidadeNiveis() {
        return niveis.size();
    }

    public void listarNiveis() {
        System.out.println("Níveis do curso " + nome + ":");
        for (Nivel nivel : niveis) {
            System.out.println(nivel.getNomeNivel() + " - " + nivel.getDificuldade());
        }
    }
}
