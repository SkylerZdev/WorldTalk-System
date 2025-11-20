import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorCursos {
    Map<Long, Curso> cursos = new HashMap<>();
    Map<Curso, List<Turma>> turmas = new HashMap<>();
    
    GerenciadorCursos() {}

    public boolean adicionarCurso(Curso curso) {
        if(cursos.containsKey(curso.getId())) {
            return false; // Curso já existe
        }
        cursos.put(curso.getId(), curso);
        turmas.put(curso, new ArrayList<>());
        return true; // Curso adicionado com sucesso
    }

    public Curso getCursoPorId(long id) {
        return cursos.get(id);
    }

    public void adicionarTurma(Curso curso, Turma turma) {
        List<Turma> listaTurmas = turmas.get(curso);
        if (listaTurmas != null) {
            listaTurmas.add(turma);
        }
    }

    public List<Turma> getTurmasPorCurso(Curso curso) {
        return turmas.get(curso);
    }

    public Map<Long, Curso> getTodosCursos() {
        return cursos;
    }
    
}
