import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Curso {
    private String nome;
    private double mensalidade;
    private List<Nivel> niveis;
    private long id;
   
    // Comparator para ordenar os níveis pela dificuldade
    private Comparator<Nivel> comparatorNivel = new Comparator< >() {
        @Override
        public int compare(Nivel n1, Nivel n2) {
            return n1.getDificuldade().compareTo(n2.getDificuldade());
        }
    };

    //Construtores
    public Curso(){}

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

    public void setId(long id) {
        this.id = id;
    }

    public void addNivel(Nivel nivel) {
        niveis.add(nivel);
        niveis.sort(comparatorNivel);
    }

    public void removerNivel(Nivel nivel) {
        if (niveis.contains(nivel)){
        niveis.remove(nivel);
        }
    }
}