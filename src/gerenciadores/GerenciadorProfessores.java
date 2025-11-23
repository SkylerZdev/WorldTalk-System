package src.gerenciadores;
import java.util.Map;

import src.modelos.Curso;
import src.modelos.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Professor> getProfessoresHabilitados(Curso curso){
        List<Professor> habilitados = new ArrayList<>();
        for (Professor prof : professores.values()) {
            if (prof.getCursosHabilitados().contains(curso)){
                habilitados.add(prof);
            }
        }
        return habilitados;
    }

    public void removerProfessorPorId(long id) {
        professores.remove(id);
    }

    public int getQuantidadeProfessores() {
        return professores.size();
    }

    public void listarProfessores() {
        for (Professor professor : professores.values()) {
            System.out.println(professor.toString());
        }
    }
}
