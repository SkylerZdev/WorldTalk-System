package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.exceptions.*;
import src.gerenciadores.GerenciadorLogins;

public class MenuPrincipal {

    public void abrirMenu(Scanner scanner, ContextoSistema sistema) {
        int opcao;

        // Cadastro Universal para Testes
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
                    abrirAreaAdmin(scanner, sistema);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void abrirAreaAdmin(Scanner scanner, ContextoSistema sistema){
        MenuAdmin menuAdmin = new MenuAdmin(scanner, sistema);
        menuAdmin.abrirMenu();
    }

    private void abrirAreaAluno(Scanner scanner, ContextoSistema sistema) {
        MenuAluno menuAluno = new MenuAluno(scanner, sistema);
        menuAluno.abrirMenu();
    }

}
