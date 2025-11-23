package src.gerenciadores;
import java.util.Map;

import src.modelos.Professor;
import src.modelos.Turma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GerenciadorTurmas {

    public GerenciadorTurmas() {}

    Map<Long, Turma> turmas = new HashMap<>();

    public boolean adicionarTurma(Turma turma) {
        if(turmas.containsKey(turma.getId())) {
            return false; // Turma já existe
        }
        turmas.put(turma.getId(), turma);
        return true; // Turma adicionada com sucesso
    }

    public Turma getTurmaPorId(long id) {
        return turmas.get(id);
    }

    public Map<Long, Turma> getTodasTurmas() {
        return turmas;
    }

    public List<Turma> getTurmasPorProfessor(Professor professor) {
        List<Turma> resultado = new ArrayList<>();
        for (Turma t : turmas.values()) {
            if (t.getProfessor().equals(professor)) {
                resultado.add(t);
            }
        }
    return resultado;
}


    public void removerTurmaPorId(long id) {
        turmas.remove(id);
    }

    public int getQuantidadeTurmas() {
        return turmas.size();
    }

    public void listarTurmas(){
        for (Turma turma : turmas.values()){
            System.out.println(turma.toString());
        }
    }
    
}
