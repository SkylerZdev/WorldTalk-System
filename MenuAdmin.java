import java.util.Scanner;

public class MenuAdmin {

    public Admin adm;

    public MenuAdmin(Admin adm) {
        this.adm = adm;
    }

    //MenuCursos / MenuProfessores / MenuBiblioteca / MenuAlunos
    // Implementar posteriormente

    public void abrirMenu(Scanner scanner) {

        int opcao;
        do {
            limpartela();
            System.out.println("Seja bem-vindo, " + adm.getUsuario());
            System.out.println("");
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Gerenciar Cursos");
            System.out.println("2 - Gerenciar Professores");
            System.out.println("3 - Gerenciar Biblioteca Virtual");
            System.out.println("4 - Gerenciar Alunos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //Menu de Cursos
                    break;
                case 2:
                    //Menu de Professores
                    break;
                case 3:
                    //Menu de Biblioteca Virtual
                    break;
                case 4:
                    //Menu de Alunos
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
