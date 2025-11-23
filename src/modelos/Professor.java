package src.modelos;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.agenda.Agenda;
import src.agenda.DiaSemana;
import src.agenda.Horario;
import src.agenda.HorarioPermitido;

public class Professor {
    private String nome;
    private final long id;
    private Agenda agenda = new Agenda();
    private Set<Curso> cursosHabilitados = new HashSet<Curso>();
    private static long idCounter = 1;

    //Construtores
    public Professor(){
        this.id = idCounter++;
    }
    
    public Professor(String nome) {
        this.nome = nome;
        this.id = idCounter++;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public List<Horario> getHorariosDisponiveis(DiaSemana dia) {
        return agenda.getHorariosDisponiveis(dia);
    }

    public Set<Curso> getCursosHabilitados() {
        return cursosHabilitados;
    }

    public void addCursoHabilitado(Curso curso) {
        this.cursosHabilitados.add(curso);
    }

    // Métodos para gerenciar horários na agenda
    public boolean ocuparHorario(DiaSemana dia, HorarioPermitido horario) {
        return agenda.ocuparHorario(dia, horario);
    }
    
    public boolean liberarHorario(DiaSemana dia, HorarioPermitido horario) {
        return agenda.liberarHorario(dia, horario);
    }
    

    @Override
    public String toString(){
        return "Id: " + id + " / Nome: " + nome;
    }

}
