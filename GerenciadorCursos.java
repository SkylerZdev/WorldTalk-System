import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorCursos {

    private Map<Long, Curso> cursos = new HashMap<>();

    private Map<Curso, List<Turma>> turmas = new HashMap<>();
    
    public GerenciadorCursos() {}

    // Adiciona um curso ao sistema
    public boolean adicionarCurso(Curso curso) {
        if (cursos.containsKey(curso.getId())) {
            return false; // Curso já existe (ID duplicado)
        }

        cursos.put(curso.getId(), curso);
        turmas.put(curso, new ArrayList<>()); // prepara a lista de turmas desse curso
        return true; // Curso adicionado com sucesso
    }

    // Recupera um curso pelo ID
    public Curso getCursoPorId(long id) {
        return cursos.get(id);
    }

    // Retorna todos os cursos em forma de lista
    public List<Curso> getTodosCursosLista() {
        return new ArrayList<>(cursos.values());
    }

    // Também pode expor o mapa inteiro, se precisar
    public Map<Long, Curso> getTodosCursos() {
        return cursos;
    }

    // Remove um curso pelo ID e também apaga suas turmas associadas
    public void removerCursoPorId(long id) {
        Curso curso = cursos.remove(id); // remove o curso do mapa de cursos
        if (curso != null) {
            turmas.remove(curso);        // remove todas as turmas associadas àquele curso
        }
    }

    // Retorna quantos cursos existem cadastrados
    public int getQuantidadeCursos() {
        return cursos.size();
    }

    // Adiciona uma turma a um curso, criando a lista se não existir e evitando duplicatas
    public void adicionarTurma(Curso curso, Turma turma) {
        // Garante que sempre exista uma lista de turmas pro curso
        List<Turma> listaTurmas = turmas.computeIfAbsent(curso, c -> new ArrayList<>());

        // Evita cadastrar a mesma turma duas vezes
        if (!listaTurmas.contains(turma)) {
            listaTurmas.add(turma);
        }
    }

    // Retorna as turmas de um curso sem permitir alterações externas
    public List<Turma> getTurmasPorCurso(Curso curso) {
        List<Turma> listaTurmas = turmas.getOrDefault(curso, new ArrayList<>());
        return Collections.unmodifiableList(listaTurmas);
    }

    // Busca cursos pelo nome exato ignorando letras maiusculas e minusculas
    public List<Curso> buscarPorNome(String nome) {
        List<Curso> resultado = new ArrayList<>();

        for (Curso curso : cursos.values()) {
            if (curso.getNome().equalsIgnoreCase(nome)) {
                resultado.add(curso);
            }
        }

        return resultado;
    }

    // Realiza busca parcial no nome do curso
    public List<Curso> buscarPorNomeParcial(String trechoNome) {
        List<Curso> resultado = new ArrayList<>();
        String trechoLower = trechoNome.toLowerCase();

        for (Curso curso : cursos.values()) {
            if (curso.getNome().toLowerCase().contains(trechoLower)) {
                resultado.add(curso);
            }
        }

        return resultado;
    }

}
