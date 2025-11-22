package src.menus.principal;

import java.util.Scanner;

import src.exceptions.*;
import src.gerenciadores.GerenciadorLogins;
import src.modelos.Admin;
import src.modelos.Aluno;

public class MenuPrincipal {

    public void abrirMenu(Scanner scanner, src.app.ContextoSistema sistema) {
        int opcao;
        do {
            limpartela();
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Perfil Aluno");
            System.out.println("2 - Perfil Administrador");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Aluno aluno = autenticadorAluno(scanner, sistema.getGerenciadorLogins());
                    if (aluno == null) {   break;  }
                    aluno.menuAluno(scanner);
                    break;
                case 2:
                    Admin adm = autenticadorAdm(scanner, sistema.getGerenciadorLogins());
                    if(adm == null) {   break;  }
                    MenuAdmin menuAdmin = new MenuAdmin(adm, sistema);
                    menuAdmin.abrirMenu(scanner);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private Admin autenticadorAdm(Scanner s, GerenciadorLogins gerenciadorLogins) {
            int opcao;
            Admin adm = null;
            do {
                limpartela();
                System.out.println("\n=== MENU ADMIN ===");
                System.out.println("1 - Login");
                System.out.println("2 - Cadastrar-se");
                System.out.println("0 - Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = s.nextInt();
                String user;
                String pass;

                switch (opcao) {
                    case 1:
                        System.out.println("Digite seu Usuario:");
                        user = s.next();
                        System.out.println("Digite sua Senha:");
                        pass = s.next();
                        try {
                        adm = gerenciadorLogins.LoginAdm(user, pass);
                        } catch (LoginException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Digite seu Usuario:");
                        user = s.next();
                        System.out.println("Digite sua Senha:");
                        pass = s.next();
                        System.out.println("Digite a Senha Universal:");
                        String senhaUniversal = s.next();
                        try {
                        gerenciadorLogins.CadastroAdm(user, pass, senhaUniversal);
                        System.out.println("");
                        System.out.println("Cadastro realizado com sucesso!");
                        } catch (CadastroException e) {
                            System.out.println("");
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 0);
            limpartela();
            return adm;
    }

    private Aluno autenticadorAluno(Scanner s, GerenciadorLogins gerenciadorLogins) {
        int opcao;
        Aluno aluno = null;
        do {
            limpartela();
            System.out.println("\n=== MENU ALUNO ===");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();
            String user;
            String pass;

            switch (opcao) {
                case 1:
                    System.out.println("Digite seu Usuario:");
                    user = s.next();
                    System.out.println("Digite sua Senha:");
                    pass = s.next();
                    try {
                    aluno = gerenciadorLogins.LoginAluno(user, pass);
                    System.out.println("Login realizado com sucesso!");
                    opcao = 0; // Sair do loop após login bem-sucedido
                    } catch (LoginException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite seu Usuario:");
                    user = s.next();
                    System.out.println("Digite sua Senha:");
                    pass = s.next();
                    try {
                    gerenciadorLogins.CadastroAluno(user, pass);
                    System.out.println("");
                    System.out.println("Cadastro realizado com sucesso!");
                    } catch (CadastroException e) {
                        System.out.println("");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
        limpartela();
        return aluno;
    }

    public static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
