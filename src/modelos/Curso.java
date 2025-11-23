package src.modelos;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Curso {
    private String nome;
    private String disciplina;
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

    public Curso(String nome, String disciplina, double mensalidade) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.mensalidade = mensalidade;
        this.id = idCounter++;
        this.niveis = new ArrayList<Nivel>();
    }

    public Curso(String nome, String disciplina, double mensalidade, List<Nivel> niveis) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.mensalidade = mensalidade;
        this.id = idCounter++;
        this.niveis = niveis;
    }

    // Getters
    public String getInfoBase() {
        return disciplina;
    }

    public String getNome() {
        return nome;
    }
    
    public String getDisciplina(){
        return disciplina;
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

    public Nivel getNivelporID(long id){
        for (Nivel n : niveis) {
            if (n.getId() == id) {
            return n;
            }
        }
        return null;
    }

    // Setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
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
        System.out.println("Níveis do curso " + disciplina + ":");
        for (Nivel nivel : niveis) {
            System.out.println("Id -  " + nivel.getId() + " | Nome - " + nivel.getNomeNivel() + " | Grau - " + nivel.getDificuldade());
        }
    }

    @Override
    public String toString(){
        return "Id - " + id + " / Nome - " + nome + " / Disciplina - " + disciplina + " / Mensalidade - " + mensalidade;
    }

}
