package src.modelos;
import java.util.List;

public class Turma {

    private final long id;
    private List<Aluno> alunos;
    private Curso curso;
    private Nivel nivel;
    private Professor professor;
    private static long idCounter = 1;

    //Construtores
    public Turma(Curso curso, Nivel nivel, Professor professor) {
        this.id = idCounter++;
        this.curso = curso;
        this.nivel = nivel;
        this.professor = professor;
    }

    //getters
    public long getId() {
        return id;
    }
    public List<Aluno> getAlunos() {
        return alunos;
    }
    public Curso getCurso() {
        return curso;
    }
    public Nivel getNivel() {
        return nivel;
    }
    public Professor getProfessor() {
        return professor;
    }
    
    //setters
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }
    
}
