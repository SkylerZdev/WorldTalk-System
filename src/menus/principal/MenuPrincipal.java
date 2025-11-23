package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.exceptions.*;
import src.gerenciadores.GerenciadorLogins;
import src.modelos.Admin;
import src.menus.MenuAluno;

public class MenuPrincipal {

    public void abrirMenu(Scanner scanner, ContextoSistema sistema) {
        int opcao;
        GerenciadorLogins g = sistema.getGerenciadorLogins();

        g.DefinirSenhaUniversal("1");
        try {
            g.CadastroAdm("1", "2", "1");
        } catch (CadastroException e) {
            System.out.println(e.getMessage());
        }

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Perfil Aluno");
            System.out.println("2 - Perfil Administrador");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    abrirAreaAluno(scanner, sistema);
                    break;

                case 2:
                    autenticadorAdm(scanner, sistema);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void autenticadorAdm(Scanner s, ContextoSistema sistema) {
        int opcao;
        Admin adm = null;
        GerenciadorLogins gerenciadorLogins = sistema.getGerenciadorLogins();

        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");
            System.out.println("3 - Definir Senha Universal");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = s.nextInt();
            s.nextLine();

            String user, pass;

            switch (opcao) {
                case 1:
                    System.out.print("Digite seu Usuario: ");
                    user = s.nextLine();
                    System.out.print("Digite sua Senha: ");
                    pass = s.nextLine();

                    try {
                        adm = gerenciadorLogins.LoginAdm(user, pass);

                        MenuAdmin menuAdmin = new MenuAdmin(adm, sistema);
                        menuAdmin.abrirMenu(s);

                    } catch (LoginException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    if (!gerenciadorLogins.isSenhaUniversalDefinida()) {
                        System.out.println("Senha universal não definida. Defina primeiro.");
                        break;
                    }

                    System.out.print("Digite seu Usuario: ");
                    user = s.nextLine();
                    System.out.print("Digite sua Senha: ");
                    pass = s.nextLine();
                    System.out.print("Digite a Senha Universal: ");
                    String senhaUniversal = s.nextLine();

                    try {
                        gerenciadorLogins.CadastroAdm(user, pass, senhaUniversal);
                        System.out.println("Cadastro realizado com sucesso!");
                    } catch (CadastroException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    if (gerenciadorLogins.isSenhaUniversalDefinida()) {
                        System.out.println("Senha universal já foi definida.");
                        break;
                    }

                    System.out.print("Digite a Senha Universal desejada: ");
                    String novaSenha = s.nextLine();
                    gerenciadorLogins.DefinirSenhaUniversal(novaSenha);

                    System.out.println("Senha universal definida com sucesso!");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void abrirAreaAluno(Scanner scanner, ContextoSistema sistema) {
        MenuAluno menuAluno = new MenuAluno();
        menuAluno.abrirMenu(scanner, sistema);
    }

}
