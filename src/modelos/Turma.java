package src.modelos;
import src.agenda.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Turma {

    private final long id;
    private List<Aluno> alunos;
    private Agenda agenda;
    private int limiteAlunos;
    private Curso curso;
    private Nivel nivel;
    private Professor professor;
    private static long idCounter = 1;
    private Map<Long, List<Double>> notasPorAluno;

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

    public Map<Long, List<Double>> getNotasAlunos(){
        return notasPorAluno;
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
        for (Aluno aluno : alunos) {
            notasPorAluno.computeIfAbsent(aluno.getId(), k -> new ArrayList<>());
        }
    }

    public void setLimiteAlunos(int limite){
        this.limiteAlunos = limite;
    }

    public boolean addAluno(Aluno aluno) {
        if(alunos.size()< limiteAlunos){
            this.alunos.add(aluno);
            notasPorAluno.computeIfAbsent(aluno.getId(), k -> new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean addAlunos(List<Aluno> lista){
        if(getVagasDisponiveis() >= lista.size()){
            this.alunos.addAll(lista);
            for (Aluno aluno : lista) {
                notasPorAluno.computeIfAbsent(aluno.getId(),k -> new ArrayList<>());
            }
            return true;
        }
        return false;
    }

    public void adicionarNota(long idAluno, double nota){
        if(!notasPorAluno.containsKey(idAluno)){
            return;
        }
        notasPorAluno.get(idAluno).add(nota);
    }

    public List<Double> getNotasAluno(long idAluno){
        if (!notasPorAluno.containsKey(idAluno)){
            return new ArrayList<>();
        }
        return notasPorAluno.get(idAluno);
    }

    public double getMediaAluno(long idAluno){
        double media = 0;
        if (!notasPorAluno.containsKey(idAluno)){
            return media;
        }
        for (Double nota : notasPorAluno.get(idAluno)) {
            media += nota;
        }
        return media/notasPorAluno.get(idAluno).size();
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
