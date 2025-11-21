import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorEmprestimos {
    Map<Long, Emprestimo> emprestimos = new HashMap<>();
    Map<MaterialBB, List<Emprestimo>> historicoMaterial = new HashMap<>();
    Map<Aluno, List<Emprestimo>> historicoAluno = new HashMap<>();
    List<Emprestimo> emprestimosAtivos = new ArrayList<>();

    public GerenciadorEmprestimos() {}

    public boolean adicionarEmprestimo(Emprestimo emprestimo) {
        if(emprestimos.containsKey(emprestimo.getId())) {
            return false; // Empréstimo já existe
        }
        emprestimos.put(emprestimo.getId(), emprestimo);
        historicoMaterial
            .computeIfAbsent(emprestimo.getMaterial(), k -> new ArrayList<>())
            .add(emprestimo);
        
        historicoAluno.computeIfAbsent(emprestimo.getAluno(), k -> new ArrayList<>())
            .add(emprestimo);

        emprestimosAtivos.add(emprestimo);
        
        return true; // Empréstimo adicionado com sucesso
    }

    public Emprestimo getEmprestimoPorId(long id) {
        return emprestimos.get(id);
    }

    public Map<Long, Emprestimo> getTodosEmprestimos() {
        return emprestimos;
    }

    public void concluirEmprestimo(long id) {
        Emprestimo emprestimo = emprestimos.get(id);
        if (emprestimo != null && emprestimosAtivos.contains(emprestimo)) {
            emprestimosAtivos.remove(emprestimo);
            emprestimo.setDevolvido(true);
        }
    }

    public int getQuantidadeEmprestimos() {
        return emprestimos.size();
    }
    
}
