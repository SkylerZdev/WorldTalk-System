package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.gerenciadores.GerenciadorAlunos;
import src.gerenciadores.GerenciadorLogins;
import src.menus.admin.MenuBiblioteca;
import src.modelos.Admin;
import src.modelos.Aluno;

public class MenuAdmin {

    private Admin adm;
    private ContextoSistema sistema;

    public MenuAdmin(Admin adm, ContextoSistema sistema) {
        this.adm = adm;
        this.sistema = sistema;
    }

    public void abrirMenu(Scanner scanner) {

        int opcao;
        do {
            System.out.println("Seja bem-vindo, " + adm.getUsuario());
            System.out.println("");
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Gerenciar Cursos");
            System.out.println("2 - Gerenciar Professores");
            System.out.println("3 - Gerenciar Biblioteca Virtual");
            System.out.println("4 - Gerenciar Alunos");
            System.out.println("0 - Deslogar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa quebra de linha

            switch (opcao) {
                case 1:
                    //Menu de Cursos
                    break;

                case 2:
                    //Menu de Professores
                    break;

                case 3:
                    MenuBiblioteca menuBiblioteca = new MenuBiblioteca(sistema, scanner);
                    menuBiblioteca.abrirMenu();
                    break;

                case 4:
                    abrirMenuAlunos(scanner);
                    break;

                case 0:
                    System.out.println("Deslogando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // Menu de Gerenciar Alunos
    private void abrirMenuAlunos(Scanner scanner) {

        GerenciadorAlunos gAlunos = sistema.getGerenciadorAlunos();
        GerenciadorLogins gLogins = sistema.getGerenciadorLogins();

        int opcao;

        do {
            System.out.println("\n=== GERENCIAR ALUNOS ===");
            System.out.println("1 - Cadastrar Novo Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Remover Aluno");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarAlunoADM(scanner, gAlunos, gLogins);
                    break;

                case 2:
                    System.out.println("\n--- Lista de Alunos ---");
                    gAlunos.listarAlunos();
                    break;

                case 3:
                    removerAlunoADM(scanner, gAlunos, gLogins);
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // Cadastrar Aluno
    private void cadastrarAlunoADM(Scanner scanner, GerenciadorAlunos gAlunos, GerenciadorLogins gLogins) {

        System.out.println("\n=== CADASTRAR NOVO ALUNO ===");

        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Usuário de login (login do aluno): ");
        String usuario = scanner.nextLine();

        System.out.print("Senha do aluno: ");
        String senha = scanner.nextLine();

        // Cria objeto Aluno
        Aluno novo = new Aluno(nome, senha);

        // Adiciona no GerenciadorAlunos
        boolean sucesso = gAlunos.adicionarAluno(novo);

        if (!sucesso) {
            System.out.println("Erro: Já existe um aluno com esse ID.");
            return;
        }

        // Registrar login
        try {
            gLogins.CadastroAluno(usuario, senha);
        } catch (Exception e) {
            System.out.println("Erro ao registrar login: " + e.getMessage());
            return;
        }

        System.out.println("Aluno cadastrado com sucesso!");
    }

    // Remover Aluno
    private void removerAlunoADM(Scanner scanner, GerenciadorAlunos gAlunos, GerenciadorLogins gLogins) {

        System.out.println("\n=== REMOVER ALUNO ===");
        System.out.println("1 - Remover por ID");
        System.out.println("2 - Remover por Nome");
        System.out.print("Escolha: ");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {

            case 1:
                System.out.print("Digite o ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Aluno alunoID = gAlunos.getAlunoPorId(id);
                if (alunoID == null) {
                    System.out.println("Aluno não encontrado.");
                    return;
                }

                gAlunos.removerAlunoPorId(id);
                gLogins.removerAlunoPorUsuario(alunoID.getNome()); // usa nome como usuário
                System.out.println("Aluno removido com sucesso.");
                break;

            case 2:
                System.out.print("Digite o nome do aluno: ");
                String nome = scanner.nextLine();

                Aluno alunoNome = gAlunos.getAlunoPorNome(nome);

                if (alunoNome == null) {
                    System.out.println("Aluno não encontrado.");
                    return;
                }

                gAlunos.removerAlunoPorId(alunoNome.getId());
                gLogins.removerAlunoPorUsuario(nome);
                System.out.println("Aluno removido com sucesso.");
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

}