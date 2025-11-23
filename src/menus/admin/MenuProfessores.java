package src.menus.admin;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import src.app.ContextoSistema;
import src.gerenciadores.GerenciadorProfessores;
import src.modelos.Professor;
import src.modelos.Curso;

public class MenuProfessores {
    Scanner scanner;
    ContextoSistema sistema;
    GerenciadorProfessores gerenciador;

    public MenuProfessores(Scanner scanner, ContextoSistema sistema){
        this.scanner = scanner;
        this.sistema = sistema;
        this.gerenciador = sistema.getGerenciadorProfessores();
    }
    public void abrirMenu() {

        int opcao;
        do {

            System.out.println("");
            System.out.println("\n--- Gerenciamento de Professores ---");
            System.out.println("1 - Adcionar Professor");
            System.out.println("2 - Editar Professor");
            System.out.println("3 - Remover Professor");
            System.out.println("4 - Listar Professores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarProfessor();
                    break;
                case 2:
                    if(gerenciador.getQuantidadeProfessores() == 0){
                        System.out.println("Nenhum Professor Cadastrado.");
                        break;
                    }
                    editarProfessor();
                    break;
                case 3:
                    if(gerenciador.getQuantidadeProfessores() == 0){
                        System.out.println("Nenhum Professor Cadastrado.");
                        break;
                    }
                    removerProfessor();
                case 4:
                    if(gerenciador.getQuantidadeProfessores() == 0){
                        System.out.println("Nenhum Professor Cadastrado.");
                        break;
                    }
                    gerenciador.listarProfessores();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void adicionarProfessor() {
        System.out.println("Digite o nome do professor:");
        String nome = scanner.nextLine();

        if(sistema.getGerenciadorCursos().getQuantidadeCursos() == 0){
            System.out.println("Nenhum Curso registrado para Ministrar.");
            System.out.println("Deseja ainda sim registrar Professor? (1 - Sim / 2 - Nao");
            int op = scanner.nextInt();
            if (op == 1){
                Professor professor = new Professor(nome);
                gerenciador.adicionarProfessor(professor);
                System.out.println("Professor Registrado");
                return;
            }
            System.out.println("Cancelando Cadastro de Professor...");
            return;
        }
        System.out.println("Selecione os cursos que o professor pode ministrar:");
        sistema.getGerenciadorCursos().listarCursos();

        Set<Curso> cursosHabilitados = new HashSet<>();

        while (true) {
            System.out.println("Digite o ID do curso (ou 0 para finalizar):");
            long idCurso = scanner.nextLong();
            if (idCurso == 0) break;

            Curso curso = sistema.getGerenciadorCursos().getCursoPorId(idCurso);
            if (curso != null) {
                cursosHabilitados.add(curso);
                System.out.println("Curso adicionado.");
            } else {
                System.out.println("Curso não encontrado.");
            }
        }

        Professor novoProfessor = new Professor(nome, cursosHabilitados);
        sistema.getGerenciadorProfessores().adicionarProfessor(novoProfessor);

        System.out.println("Professor adicionado com sucesso.");
    }
    
    private void editarProfessor() {
        gerenciador.listarProfessores();
        System.out.println("Digite o ID do professor que deseja editar:");
        long id = scanner.nextLong();
        scanner.nextLine();

        Professor prof = gerenciador.getProfessorPorId(id);
        if (prof == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        System.out.println("Esse é o professor que deseja editar? (1 - Sim // 2 - Não)");
        System.out.println(prof.toString());
        int confirmacao = scanner.nextInt();
        scanner.nextLine();

        if (confirmacao != 1) {
            System.out.println("Edição cancelada.");
            return;
        }

        // Nome
        System.out.println("Digite o novo nome (atual: " + prof.getNome() + ")");
        System.out.println("(Digite '-' para manter o nome atual)");
        String novoNome = scanner.nextLine();
        if (!novoNome.equals("-")) {
            prof.setNome(novoNome);
        }

        // Cursos habilitados
        System.out.println("Deseja editar os cursos habilitados?");
        System.out.println("1 - Sim // 2 - Não");
        int opcCursos = scanner.nextInt();

        if (opcCursos == 1) {
            Set<Curso> novosCursos = new HashSet<>();

            System.out.println("Lista de cursos disponíveis:");
            sistema.getGerenciadorCursos().listarCursos();

            while (true) {
                System.out.println("Digite o ID do curso (ou 0 para finalizar):");
                long idCurso = scanner.nextLong();
                if (idCurso == 0) break;

                Curso curso = sistema.getGerenciadorCursos().getCursoPorId(idCurso);

                if (curso != null) {
                    novosCursos.add(curso);
                    System.out.println("Curso adicionado.");
                }   else {
                    System.out.println("Curso não encontrado.");
                }
            }

            prof.setCursosHabilitados(novosCursos);
        }

        System.out.println("Professor editado com sucesso.");
    }


    private void removerProfessor() {
        gerenciador.listarProfessores();

        System.out.println("Digite o ID do professor que deseja remover:");
        long id = scanner.nextLong();
        scanner.nextLine();

        Professor prof = gerenciador.getProfessorPorId(id);
        if (prof == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        if(!sistema.getGerenciadorTurmas().getTurmasPorProfessor(prof).isEmpty()){
            System.out.println("Remoção Impossivel: Turmas Registradas com este Professor.");
            return;
        }

        System.out.println("Tem certeza que deseja remover este professor?");
        System.out.println(prof.toString());
        System.out.println("1 - Sim // 2 - Não");
        int confirm = scanner.nextInt();
        scanner.nextLine();

        if (confirm != 1) {
            System.out.println("Remoção cancelada.");
            return;
        }

        gerenciador.removerProfessorPorId(id);
        System.out.println("Professor removido com sucesso.");
    }
}