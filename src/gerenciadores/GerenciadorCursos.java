package src.gerenciadores;
import java.util.Map;

import src.modelos.Curso;
import src.modelos.Turma;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorCursos {

    private Map<Long, Curso> cursos = new HashMap<>();
    private Map<Curso, List<Long>> turmas = new HashMap<>();
    
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

    // Remove um curso pelo ID e Retorna o Id das turmas associadas
    public List<Long> removerCursoPorId(long id) {
        Curso curso = cursos.remove(id);    // remove o curso do mapa de cursos
        if (curso != null) {
            return turmas.remove(curso);        // remove todas as turmas associadas àquele curso e retorna-as
        }
        return new ArrayList<>();
    }

    // Retorna quantos cursos existem cadastrados
    public int getQuantidadeCursos() {
        return cursos.size();
    }

    // Adiciona uma turma a um curso, criando a lista se não existir e evitando duplicatas
    public void adicionarTurma(Curso curso, Turma turma) {
        // Garante que sempre exista uma lista de turmas pro curso
        List<Long> listaTurmas = turmas.computeIfAbsent(curso, c -> new ArrayList<>());

        // Evita cadastrar a mesma turma duas vezes
        if (!listaTurmas.contains(turma.getId())) {
            listaTurmas.add(turma.getId());
        }
    }

    public boolean adicionarTurmaPorId(Long id, Turma turma){
        Curso c = cursos.get(id);
        if(c == null){
            return false;
        }
        List<Long> lista = turmas.get(c);
        if (lista.contains(turma.getId())){
            return false;
        }
        lista.add(turma.getId());
        return true;
    }

    // Retorna as turmas de um curso sem permitir alterações externas
    public List<Long> getTurmasPorCurso(Curso curso) {
        List<Long> listaTurmas = turmas.getOrDefault(curso, new ArrayList<>());
        return listaTurmas;
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

    // Metodo de Listar Cursos
    public void listarCursos(){
        for (Curso curso : cursos.values()) {
            System.out.println(curso.toString());
        }
    }
}
