package src.menus.principal;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.exceptions.CadastroException;
import src.exceptions.LoginException;
import src.gerenciadores.GerenciadorLogins;
import src.menus.admin.MenuAdm_Alunos;
import src.menus.admin.MenuBiblioteca;
import src.menus.admin.MenuCursos;
import src.menus.admin.MenuProfessores;
import src.modelos.Admin;

public class MenuAdmin {

    private ContextoSistema sistema;
    private Scanner scanner;
    private GerenciadorLogins gerenciadorLogins;

    public MenuAdmin(Scanner scanner, ContextoSistema sistema) {
        this.scanner = scanner;
        this.sistema = sistema;
        this.gerenciadorLogins = sistema.getGerenciadorLogins();
    }

    public void abrirMenu() {
        int opcao;
        GerenciadorLogins gerenciadorLogins = sistema.getGerenciadorLogins();

        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");
            System.out.println("3 - Definir Senha Universal");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); //Limpar lixo da Entrada

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;

                case 2:
                    cadastrarAdmin();
                    break;

                case 3:
                    if (gerenciadorLogins.isSenhaUniversalDefinida()) {
                        System.out.println("Senha universal já foi definida.");
                        break;
                    }

                    System.out.print("Digite a Senha Universal desejada: ");
                    String novaSenha = scanner.nextLine();
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

     private void realizarLogin() {
        

        System.out.println("\n=== LOGIN DO ADMIN ===");
        System.out.print("Usuário: ");
        String user = scanner.nextLine();

        System.out.print("Senha: ");
        String pass = scanner.nextLine();

        try {
            Admin adm = gerenciadorLogins.LoginAdm(user, pass);
            System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + adm.getUsuario() + "!");
            pausar(scanner);
            menuAdminLogado(adm);
        } catch (LoginException e) {
            System.out.println("\nErro no login: " + e.getMessage());
            pausar(scanner);
        }
    }

    private void cadastrarAdmin(){
        if (!gerenciadorLogins.isSenhaUniversalDefinida()) {
            System.out.println("Senha universal não definida. Defina primeiro.");
            return;
        }
            
        System.out.print("Digite seu Usuario: ");
        String user = scanner.nextLine();

        System.out.print("Digite sua Senha: ");
        String pass = scanner.nextLine();

        System.out.print("Digite a Senha Universal: ");
        String senhaUniversal = scanner.nextLine();

        try {
            gerenciadorLogins.CadastroAdm(user, pass, senhaUniversal);
            System.out.println("Cadastro realizado com sucesso!");
        }   catch (CadastroException e) {   System.out.println(e.getMessage());     }      
    }

    private void menuAdminLogado(Admin adm) {

        int opcao;
        do {
            System.out.println("Seja bem-vindo, " + adm.getUsuario());
            System.out.println("");
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Gerenciar Cursos");
            System.out.println("2 - Gerenciar Professores");
            System.out.println("3 - Gerenciar Biblioteca Virtual");
            System.out.println("4 - Gerenciar Alunos");
            System.out.println("5 - Gerenciar Turmas");
            System.out.println("0 - Deslogar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa quebra de linha

            switch (opcao) {
                case 1:
                    MenuCursos menuCursos = new MenuCursos(scanner, sistema);
                    menuCursos.abrirMenu();
                    break;

                case 2:
                    MenuProfessores menuProfessores = new MenuProfessores(scanner, sistema);
                    menuProfessores.abrirMenu();
                    break;

                case 3:
                    MenuBiblioteca menuBiblioteca = new MenuBiblioteca(sistema, scanner);
                    menuBiblioteca.abrirMenu();
                    break;

                case 4:
                    MenuAdm_Alunos menuAluno = new MenuAdm_Alunos(sistema, scanner);
                    menuAluno.abrirMenuAluno();
                    break;
                    
                case 5:
                    //MenuTurmas menuTurmas = new MenuTurmas(sistema, scanner);
                    //menuTurmas.abrirMenu();
                case 0:
                    System.out.println("Deslogando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

     private static void pausar(Scanner scanner) {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}