package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.exceptions.LoginException;
import src.gerenciadores.GerenciadorLogins;
import src.modelos.Aluno;

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
            System.out.println("1 - Ver opções da biblioteca (placeholder)");
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
                    System.out.println("Funcionalidade ainda não implementada.");
                    pausar(scanner);
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
