package src.menus.admin.mainAdmin;

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
            System.out.println("4 - Editar Aluno");
            // Adicionar Notas.
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
                
                case 4:
                    if (gAlunos.getQuantidadeAlunos() == 0){
                        System.out.println("Nenhum Aluno Cadastrado para Editar");
                        break;
                    }
                    System.out.println("\n--- Lista de Alunos ---");
                    gAlunos.listarAlunos();
                    editarAluno();
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
    private void editarAluno(){
        System.out.println("Digite o ID do Aluno que deseja editar:");
        long id = scanner.nextLong();
        Aluno aluno = sistema.getGerenciadorAlunos().getAlunoPorId(id);
        if (aluno == null) {
            System.out.println("Aluno com ID " + id + " não encontrado.");
            return;
        }
        
        System.out.println("Esse é o Aluno que você deseja editar? (1 - Sim // 2 - Nao)");
        scanner.nextLine(); 
        System.out.println(aluno.toString());
        int confirmacao = scanner.nextInt();
        if (confirmacao != 1) {
            System.out.println("Edição cancelada.");
            return;
        }
        scanner.nextLine();
        System.out.println("Digite o novo nome do Aluno (atual: " + aluno.getNome() + "):");
        System.out.println("(Digite '-' para manter o nome atual)");
        System.out.println("");
        String novoNome = scanner.nextLine();
        if (!novoNome.equals("-")) {
            aluno.setNome(novoNome);
        }


        System.out.println("O Aluno é VIP? (1 - Sim // 2 - Não // 3 - Manter atual)");
        int exclusivoVipInput = scanner.nextInt();
        if (exclusivoVipInput == 1) {
            if(!aluno.isVip()){
                sistema.getGerenciadorInscricoes().tornarVip(aluno);
            }
            aluno.setVip(true);
        } else if (exclusivoVipInput == 2) {
            if (aluno.isVip()){
                sistema.getGerenciadorInscricoes().tornarNormal(aluno);
            }
        } // Se for 3, mantém o valor atual
        System.out.println("Aluno editado com sucesso.");
    }
}
