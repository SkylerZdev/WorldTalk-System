import java.util.Scanner;

public class Admin {
    private String usuario;
    private String senha;

    public Admin(){}
    
    public Admin(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public void menuAdmin(Scanner scanner) {
        int opcao;
        do {
            limpartela();
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

    //Getters e Setters
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
