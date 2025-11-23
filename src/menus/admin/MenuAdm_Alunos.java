package src.menus.admin;

import java.util.Scanner;

import src.gerenciadores.GerenciadorAlunos;
import src.gerenciadores.GerenciadorLogins;
import src.modelos.Aluno;
import src.app.ContextoSistema;

public class MenuAdm_Alunos {

    Scanner scanner;
    ContextoSistema sistema;

    public MenuAdm_Alunos(ContextoSistema sistema, Scanner scanner){
        this.scanner = scanner;
        this.sistema = sistema;
    }

     public void abrirMenuAluno() {

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
                    if (gAlunos.getQuantidadeAlunos() == 0){
                        System.out.println("Nenhum Aluno Cadastrado");
                        break;
                    }
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
        Aluno novo = new Aluno(nome, usuario ,senha);

        System.out.println("O Aluno é Vip? (1 - Sim / 2 - Não)");
        int escolha = scanner.nextInt();
        if (escolha == 1){
            novo.setVip(true);
        }
        
        // Registrar login
        try {
            gLogins.CadastroAluno(novo);
        } catch (Exception e) {
            System.out.println("Erro ao registrar login: " + e.getMessage());
            return;
        }
        
        // Adiciona no GerenciadorAlunos
        boolean sucesso = gAlunos.adicionarAluno(novo);

        if (!sucesso) {
            System.out.println("Erro: Já existe um aluno com esse ID.");
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
