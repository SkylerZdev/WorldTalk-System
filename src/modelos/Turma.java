package src.modelos;
import src.agenda.*;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private final long id;
    private List<Aluno> alunos;
    private Agenda agenda;
    private int limiteAlunos;
    private Curso curso;
    private Nivel nivel;
    private Professor professor;
    private static long idCounter = 1;

    //Construtores
    public Turma(Curso curso, Nivel nivel, Professor professor, int limiteAlunos, Agenda agenda) {
        this.id = idCounter++;
        this.curso = curso;
        this.nivel = nivel;
        this.professor = professor;
        this.limiteAlunos = limiteAlunos;
        this.agenda = agenda;
        this.alunos = new ArrayList<>();
    }

    //getters
    public Agenda getAgenda(){
        return agenda;
    }

    public long getId() {
        return id;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public int getQuantidadeAlunos(){
        return alunos.size();
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

    public int getVagasDisponiveis(){
        return limiteAlunos-alunos.size();
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

    public boolean addAluno(Aluno aluno) {
        if(alunos.size()< limiteAlunos){
            this.alunos.add(aluno);
            return true;
        }
        return false;
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }

    public void listarAlunos(){
        for (Aluno aluno : alunos) {
            System.out.println(aluno.toString());
        }
    }

    public void exibirAgenda(){
        agenda.exibirAgenda();
    }
    
    @Override
    public String toString() {
        return "Id: " + id + " / Professor: " + professor.getNome() + " / Curso: " + curso + " / Nivel: " + nivel + " / Num. Alunos: " + alunos.size() + " / Vagas Disponiveis: " + (limiteAlunos-alunos.size());
    }
}
