package src.modelos;
import java.util.Scanner;

public class Aluno {
    private String nome;
    private final long id;
    private String senha;
    private boolean isVip;
    private static long idCounter = 1; // Contador simples para gerar IDs únicos

    public Aluno(){
        this.id = idCounter++;
    }

    public Aluno(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.id = idCounter++;
        this.isVip = false;
    }

    public void menuAluno(Scanner scanner) {
        int opcao;
        do {
            limpartela();
            System.out.println("\n--- Menu Aluno ---");
            System.out.println("1 -  -");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //Definir Posteriormente
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public long getId() {
        return id;
    }

    private static void limpartela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}