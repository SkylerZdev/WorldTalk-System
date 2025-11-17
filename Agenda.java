import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;

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

    public EnumMap<DiaSemana, List<Horario>> getHorariosPorDia() {
        return horariosPorDia;
    }

    public void setHorariosPorDia(EnumMap<DiaSemana, List<Horario>> horariosPorDia) {
        this.horariosPorDia = horariosPorDia;
    }
    
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
}
