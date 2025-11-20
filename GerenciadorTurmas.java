import java.util.Map;
import java.util.HashMap;

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

    public void removerTurmaPorId(long id) {
        turmas.remove(id);
    }

    public int getQuantidadeTurmas() {
        return turmas.size();
    }
    
}
