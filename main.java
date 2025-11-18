import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorLogins gerenciadorLogins = new GerenciadorLogins();
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
                    Aluno aluno = menuAluno(scanner, gerenciadorLogins);
                    if (aluno == null) {   break;  }
                    aluno.menuAluno(scanner);
                    break;
                case 2:
                   menuAdmin(scanner, gerenciadorLogins); 
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
    private static Aluno menuAluno(Scanner s, GerenciadorLogins gerenciadorLogins) {
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


    private static void menuAdmin(Scanner s, GerenciadorLogins gerenciadorLogins) {
            int opcao;
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
                        gerenciadorLogins.LoginAdm(user, pass);
                        } catch (LoginException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Digite seu Usuario:");
                        user = s.next();
                        System.out.println("Digite sua Senha:");
                        pass = s.next();
                        System.out.println("digite a Senha Universal:");
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
    }

    private static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
