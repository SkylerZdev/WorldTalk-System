package src.agenda;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public class Agenda {
    EnumMap<DiaSemana, List<Horario>> horariosPorDia;
    
    public Agenda() {
        horariosPorDia = new EnumMap<>(DiaSemana.class);

        // Cria a lista de horários para cada dia automaticamente
        for (DiaSemana dia : DiaSemana.values()) {
            List<Horario> lista = new ArrayList<>();

            for (HorarioPermitido h : HorarioPermitido.values()) {
                lista.add(new Horario(h));
            }

            horariosPorDia.put(dia, lista);
        }
    }

    // Getters e Setters
    public EnumMap<DiaSemana, List<Horario>> getTodosHorarios() {
        return horariosPorDia;
    }

    public void setHorariosPorDia(EnumMap<DiaSemana, List<Horario>> horariosPorDia) {
        this.horariosPorDia = horariosPorDia;
    }
    
    //Retorna uma lista de horários disponíveis para um dia específico

    public List<Horario> getHorariosDisponiveis(DiaSemana dia) {
        List<Horario> disponiveis = new ArrayList<>();
        List<Horario> todosHorarios = horariosPorDia.get(dia);

        for (Horario horario : todosHorarios) {
            if (horario.isDisponivel()) {
                disponiveis.add(horario);
            }
        }

        return disponiveis;
    }

    public List<Horario> getHorariosOcupados(DiaSemana dia){
        List<Horario> ocupados = new ArrayList<>();
        List<Horario> todosHorarios = horariosPorDia.get(dia);

        for (Horario horario : todosHorarios) {
            if (horario.isDisponivel()) {
                ocupados.add(horario);
            }
        }

        return ocupados;
    }
    
    public List<Horario> getTodosHorariosOcupados(){
        List<Horario> ocupados = new ArrayList<>();
        
        for (DiaSemana dia : DiaSemana.values()) {
            ocupados.addAll(getHorariosOcupados(dia));   
        }
        return ocupados;
    }


    public List<Horario> getHorariosDoDia(DiaSemana dia){
        return horariosPorDia.get(dia);
    }

    public void listarHorariosDia(DiaSemana dia){
        for (Horario h : horariosPorDia.get(dia)) {
            System.out.println(h.toString());
        }
    }

    //Compara duas Agendas para checar se são compativeis
    public boolean isCompativelCom(Agenda outra){
        return listarConflitosCom(outra).isEmpty();
    }

    //Retorna Set de Horarios conflitantes
    public Set<Horario> listarConflitosCom(Agenda outra){
        Set<Horario> setThis = new HashSet<>(this.getTodosHorariosOcupados());
        setThis.retainAll(outra.getTodosHorariosOcupados());
        return setThis;
    }

    public boolean ocuparHorario(DiaSemana dia, HorarioPermitido horario) {
        List<Horario> horariosDia = this.getHorariosDoDia(dia);
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

    public void ocuparHorarios(DiaSemana dia, Collection<Horario> hs){
        for (Horario horario : hs) {
            ocuparHorario(dia, horario.getHorarioPermitido());
        }
    }

    public boolean liberarHorario(DiaSemana dia, HorarioPermitido horario) {
        List<Horario> horariosDia = this.getHorariosDoDia(dia);
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

    public void liberarHorarios(DiaSemana dia, Collection<Horario> hs){
        for (Horario horario : hs) {
            liberarHorario(dia, horario.getHorarioPermitido());
        }
    }
}
