package src.menus.admin;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.gerenciadores.GerenciadorTurmas;

public class MenuTurmas {
    
    Scanner scanner;
    ContextoSistema sistema;
    GerenciadorTurmas gerenciador;
    
    public MenuTurmas(ContextoSistema sistema, Scanner scanner){
        this.scanner = scanner;
        this.sistema = sistema;
        gerenciador = sistema.getGerenciadorTurmas();
    }

    public void abrirMenu() {

        int opcao;
        do {
            System.out.println("");
            System.out.println("\n--- Gerenciar Turmas ---");
            System.out.println("1 - Cadastrar Turma");
            System.out.println("2 - Editar Turma");
            System.out.println("3 - Listar Turmas");
            System.out.println("4 - Remover Turma");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //cadastrarTurma();
                    break;
                case 2:
                    //editarTurma();
                    break;
                case 3:
                    if (gerenciador.getQuantidadeTurmas() == 0){
                        System.out.println("Nenhuma Turma Cadastrada");
                    } else {
                        gerenciador.listarTurmas();
                    }
                    break;
                case 4:
                    //removerTurma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public void cadastrarTurma(){
        System.out.println("Digite o Nome da Turma que deseja cadastrar");
        String nome = scanner.nextLine();
        //sistema.getGerenciadorCursos().listarCursos();
        //sistema.getGerenciadorAlunos().listarAlunos();

    }

}