import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Professor {
    private String nome;
    private long id;
    private Agenda agenda = new Agenda();
    private Set<Curso> cursosHabilitados = new HashSet<Curso>();

    //Construtores
    public Professor(){}
    
    public Professor(String nome, long id) {
        this.nome = nome;
        this.id = id;
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

    public void setId(long id) {
        this.id = id;
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
        List<Horario> horariosDia = agenda.getHorariosPorDia().get(dia);
        for (Horario h : horariosDia) {
            if (h.getHorarioPermitido() == horario) {
                if(!h.isDisponivel()) {
                    return false; // Horário já ocupado
                }
                h.setDisponivel(false);
                return true; // Horário ocupado com sucesso
            }
        }
        return false; // Horário não encontrado
    }
    
    public boolean liberarHorario(DiaSemana dia, HorarioPermitido horario) {
        List<Horario> horariosDia = agenda.getHorariosPorDia().get(dia);
        for (Horario h : horariosDia) {
            if (h.getHorarioPermitido() == horario) {
                if(h.isDisponivel()) {
                    return false; // Horário já livre
                }
                h.setDisponivel(true);
                return true; // Horário liberado com sucesso
            }
        }
        return false; // Horário não encontrado
    }

}
