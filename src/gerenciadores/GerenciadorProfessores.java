package src.gerenciadores;
import java.util.Map;

import src.modelos.Professor;

import java.util.HashMap;

public class GerenciadorProfessores {

    public GerenciadorProfessores() {}

    Map<Long, Professor> professores = new HashMap<>();


    public boolean adicionarProfessor(Professor professor) {
        if(professores.containsKey(professor.getId())) {
            return false; // Professor já existe
        }
        professores.put(professor.getId(), professor);
        return true; // Professor adicionado com sucesso
    }

    public Professor getProfessorPorId(long id) {
        return professores.get(id);
    }

    public Map<Long, Professor> getTodosProfessores() {
        return professores;
    }

    public void removerProfessorPorId(long id) {
        professores.remove(id);
    }

    public int getQuantidadeProfessores() {
        return professores.size();
    }

}
