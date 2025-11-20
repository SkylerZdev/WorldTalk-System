import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GerenciadorAlunos {

    
    private Map<Long, Aluno> alunos = new HashMap<>();
    private Map<Long, List<Turma>> alunoTurmas = new HashMap<>();

    public GerenciadorAlunos() {}

    public boolean adicionarAluno(Aluno aluno) {
        if(alunos.containsKey(aluno.getId())) {
            return false; // Aluno já existe
        }
        alunos.put(aluno.getId(), aluno);
        return true; // Aluno adicionado com sucesso
    }

    public Aluno getAlunoPorId(long id) {
        return alunos.get(id);
    }

    public Map<Long, Aluno> getTodosAlunos() {
        return alunos;
    }

    public void removerAlunoPorId(long id) {
        alunos.remove(id);
    }

    public int getQuantidadeAlunos() {
        return alunos.size();
    }
    

    public void adicionarTurmaParaAluno(long alunoId, Turma turma) {
        List<Turma> turmas = alunoTurmas.getOrDefault(alunoId, new ArrayList<>());
        turmas.add(turma);
        alunoTurmas.put(alunoId, turmas);
    }

    public List<Turma> getTurmasDoAluno(long alunoId) {
        return alunoTurmas.getOrDefault(alunoId, new ArrayList<>());
    }

    public void removerTurmaDoAluno(long alunoId, Turma turma) {
        List<Turma> turmas = alunoTurmas.get(alunoId);
        if (turmas != null) {
            turmas.remove(turma);
        }
    }

}
