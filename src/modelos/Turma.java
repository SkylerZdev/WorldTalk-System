package src.modelos;
import src.agenda.*;
import java.util.List;

public class Turma {

    private final long id;
    private List<Aluno> alunos;
    private Agenda agenda = new Agenda();
    private int limiteAlunos;
    private Curso curso;
    private Nivel nivel;
    private Professor professor;
    private static long idCounter = 1;

    //Construtores
    public Turma(Curso curso, Nivel nivel, Professor professor, int limiteAlunos) {
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

    public int getLimiteAlunos(){
        return limiteAlunos;
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

    public List<Horario> getHorariosDisponiveis(DiaSemana dia) {
        return agenda.getHorariosDisponiveis(dia);
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

    public void setLimiteAlunos(int limite){
        this.limiteAlunos = limite;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }
    
    @Override
    public String toString() {
        return "Id: " + id + " / Professor: " + professor + " / Curso: " + curso + " / Nivel: " + nivel + " / Num. Alunos: " + alunos.size();
    }
}
