import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Aluno aluno = new Aluno(); // Instância da classe Aluno
        Admin admin = new Admin(); // Instância da classe Administrador

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
                    aluno.menuAluno(scanner); // Chama o submenu do Aluno
                    break;
                case 2:
                    admin.menuAdmin(scanner); // Chama o submenu do Administrador
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
