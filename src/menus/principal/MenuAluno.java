package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.exceptions.LoginException;
import src.gerenciadores.GerenciadorLogins;
import src.modelos.Aluno;
import src.modelos.Curso;
import src.modelos.Turma;

public class MenuAluno {
    Scanner scanner;
    ContextoSistema sistema;

    public MenuAluno(Scanner scanner, ContextoSistema sistema){
        this.scanner = scanner;
        this.sistema = sistema;
    }

    // Menu inicial da área do aluno
    public void abrirMenu() {
        int opcao;

        do {
            System.out.println("\n=== ÁREA DO ALUNO ===");
            System.out.println("1 - Login");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            // lê a opção
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("Escolha uma opção: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;

                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    pausar(scanner);
            }

        } while (opcao != 0);
    }

    // Faz o login do aluno usando o GerenciadorLogins
    private void realizarLogin() {
        GerenciadorLogins gerenciadorLogins = sistema.getGerenciadorLogins();

        System.out.println("\n=== LOGIN DO ALUNO ===");
        System.out.print("Usuário: ");
        String user = scanner.nextLine();

        System.out.print("Senha: ");
        String pass = scanner.nextLine();

        try {
            Aluno aluno = gerenciadorLogins.LoginAluno(user, pass);
            System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + aluno.getNome() + "!");
            pausar(scanner);
            menuAlunoLogado(aluno);
        } catch (LoginException e) {
            System.out.println("\nErro no login: " + e.getMessage());
            pausar(scanner);
        }
    }

    // Menu que aparece depois que o aluno está logado
    private void menuAlunoLogado(Aluno aluno) {
        int opcao;

        do {
            System.out.println("\n=== MENU DO ALUNO ===");
            System.out.println("Aluno: " + aluno.getNome() + (aluno.isVip() ? " (VIP)" : ""));
            System.out.println("1 - Ver Materiais da Biblioteca");
            System.out.println("2 - Ver Cursos Disponiveis");
            System.out.println("3 - Realizar Pre-Inscrição");
            System.out.println("4 - Checar Notas");
            System.out.println("0 - Sair da conta");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("Escolha uma opção: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (sistema.getGerenciadorBiblioteca().getQuantidadeMateriais()==0) {
                        System.out.println("Nenhum material cadastrado.");
                        break;
                    }
                    sistema.getGerenciadorBiblioteca().ListarMateriais();
                    pausar(scanner);
                    break;
                case 2:
                    if (sistema.getGerenciadorCursos().getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado");
                        break;
                    } 
                    sistema.getGerenciadorCursos().listarCursos();
                    break;
                case 3:
                    if (sistema.getGerenciadorCursos().getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado para Inscrição");
                        break;
                    } 
                    sistema.getGerenciadorCursos().listarCursos();
                    System.out.print("Digite o Id do Curso desejado: ");
                    Curso curso = sistema.getGerenciadorCursos().getCursoPorId(scanner.nextLong());
                    if(curso == null){
                        System.out.println("Nenhum Curso Encontrado com esse ID");
                        break;
                    }
                    boolean isCadastrado = false;
                    for (Long l : sistema.getGerenciadorCursos().getTurmasPorCurso(curso)) {
                        if (sistema.getGerenciadorTurmas().getTurmaPorId(l).getAlunos().contains(aluno)){
                            System.out.println("Você ja está Cadastrado em uma turma desse Curso.");
                            isCadastrado = true;
                            break;
                        }
                    }
                    if (isCadastrado){
                        break;
                    }
                    sistema.getGerenciadorInscricoes().adicionarInscricao(aluno, curso);
                    System.out.println("Pre-Inscrição Adicionada com Sucesso.");
                    break;
                
                case 4:
                    if (sistema.getGerenciadorAlunos().getTurmasDoAluno(aluno.getId()).isEmpty()){
                        System.out.println("Nenhum Curso Cadastrado");
                        break;
                    }
                    for (Turma t : sistema.getGerenciadorAlunos().getTurmasDoAluno(aluno.getId())){
                        System.out.println("Curso: " + t.getCurso().getNome());
                        int x = 1;
                        for (double nota : t.getNotasAluno(aluno.getId())) {
                            System.out.println("P" + x++ + " | " + nota);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saindo da conta do aluno...");
                    pausar(scanner);
                    break;

                default:
                    System.out.println("Opção inválida.");
                    pausar(scanner);
            }

        } while (opcao != 0);
    }

    private static void pausar(Scanner scanner) {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
