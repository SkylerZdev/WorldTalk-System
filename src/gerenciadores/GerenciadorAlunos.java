package src.gerenciadores;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.modelos.Aluno;
import src.modelos.Turma;

import java.util.HashMap;
import java.util.Collections;

public class GerenciadorAlunos {

    private Map<Long, Aluno> alunos = new HashMap<>();

    private Map<Long, List<Turma>> alunoTurmas = new HashMap<>();

    public GerenciadorAlunos() {}

    public boolean adicionarAluno(Aluno aluno) {
        if(alunos.containsKey(aluno.getId())) {
            return false; // aluno já existe no sistema
        }
        alunos.put(aluno.getId(), aluno);
        return true; // aluno cadastrado com sucesso
    }

    public Aluno getAlunoPorId(long id) {
        return alunos.get(id);
    }

    // Retorna todos os alunos em uma lista
    public List<Aluno> getTodosAlunosLista() {
        return new ArrayList<>(alunos.values());
    }

    // Remove o aluno pelo ID e também limpa suas turmas associadas
    public void removerAlunoPorId(long id) {
        alunos.remove(id);                // remove aluno
        alunoTurmas.remove(id);           // remove vínculos com turmas
    }

    // Retorna quantos alunos existem cadastrados
    public int getQuantidadeAlunos() {
        return alunos.size();
    }
    
    // Adiciona uma turma ao aluno, criando a lista caso não exista e evitando duplicatas
    public void adicionarTurmaParaAluno(long alunoId, Turma turma) {
        List<Turma> turmas = alunoTurmas.computeIfAbsent(alunoId,k-> new ArrayList<>());
        if (!turmas.contains(turma)) { // evita colocar a mesma turma duas vezes
            turmas.add(turma);
            alunoTurmas.put(alunoId, turmas);
        }
    }

    // Retorna as turmas do aluno sem permitir modificações acidentais
    public List<Turma> getTurmasDoAluno(long alunoId) {
        List<Turma> turmas = alunoTurmas.getOrDefault(alunoId, new ArrayList<>());
        return Collections.unmodifiableList(turmas);
    }

    public boolean alunoExiste(long alunoId) {
        return alunos.containsKey(alunoId);
    }

    public void listarAlunos() {
        for (Aluno aluno : alunos.values()) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", VIP: " + aluno.isVip());
        }
    }

    public void listarAlunosVip() {
        for (Aluno aluno : alunos.values()) {
            if (aluno.isVip()) {
                System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
            }
        }
    }

    // Remove uma turma específica da lista de um aluno
    public void removerTurmaDoAluno(long alunoId, Turma turma) {
        List<Turma> turmas = alunoTurmas.get(alunoId);
        if (turmas != null) {
            turmas.remove(turma); // remove a turma da lista (se existir)
        }
    }

}