import java.util.Scanner;

public class Admin {
    private String usuario;
    private String senha;

    public void menuAdmin(Scanner scanner) {
        int opcao;
        do {
            limpartela();
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //Login
                    break;
                case 2:
                    //Cadastro
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    private static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
