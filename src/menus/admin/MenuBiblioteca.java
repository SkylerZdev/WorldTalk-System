package src.menus.admin;
import java.util.Scanner;
import src.modelos.*;
import src.app.ContextoSistema;

public class MenuBiblioteca {

    private Admin adm;
    private ContextoSistema sistema;

    public MenuBiblioteca(Admin adm, ContextoSistema sistema) {
        this.adm = adm;
        this.sistema = sistema;
    }

    //MenuCursos / MenuProfessores / MenuBiblioteca / MenuAlunos
    // Implementar posteriormente

    public void abrirMenu(Scanner scanner) {

        int opcao;
        do {
            limpartela();
            System.out.println("");
            System.out.println("\n--- Biblioteca Virtual ---");
            System.out.println("1 - Gerenciar Materiais");
            System.out.println("2 - Gerenciar Empréstimos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //Menu de Materiais
                    break;
                case 2:
                    //Menu de Emprestimos
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
