package src.menus.admin.turmas;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.modelos.Aluno;
import src.modelos.Turma;

public class MenuAlunosTurma{

    private ContextoSistema sistema;
    private Scanner scanner;
    private Turma turma;

    public MenuAlunosTurma(ContextoSistema sistema, Scanner scanner, Turma turma) {
        this.sistema = sistema;
        this.scanner = scanner;
        this.turma = turma;
    }

    public void abrirMenu() {

        int opcao;
        do {

            System.out.println("");
            System.out.println("\n=== GERENCIAR ALUNOS DA TURMA ===");
            System.out.println("1 - Listar alunos da turma");
            System.out.println("2 - Adicionar aluno manualmente");
            System.out.println("3 - Remover aluno da turma");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    turma.listarAlunos();
                    break;
                case 2:
                    forceAdd();
                    break;
                case 3:
                    removeAluno();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void forceAdd(){
        if (sistema.getGerenciadorAlunos().getQuantidadeAlunos() == 0){
            System.out.println("Nenhum Aluno Cadastrado");
            return;
        }
        if(turma.getVagasDisponiveis()==0){
            System.out.println("Turma está Cheia");
            return;
        }
        sistema.getGerenciadorAlunos().listarAlunos();
        System.out.print("Digite o ID do Aluno que será Adicionado (0 para Cancelar): ");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (id == 0 ){
            System.out.println("Cancelando...");
            return;
        }
        Aluno alunoID = sistema.getGerenciadorAlunos().getAlunoPorId(id);
        if (alunoID == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        if(turma.getAlunos().contains(alunoID)){
            System.out.println("Aluno ja Cadastrado na turma.");
            return;
        }
        turma.addAluno(alunoID);
        System.out.println("Aluno Adicionado com sucesso.");
    }

    public void removeAluno(){
        if (turma.getQuantidadeAlunos() == 0){
            System.out.println("Turma não tem Alunos para Remoção");
            return;
        }
        turma.listarAlunos();
        System.out.print("Digite o ID do Aluno que será Removido (0 para Cancelar): ");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (id == 0 ){
            System.out.println("Cancelando...");
            return;
        }
        Aluno alunoID = sistema.getGerenciadorAlunos().getAlunoPorId(id);
        if (alunoID == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        System.out.println("Deseja realmente remover o seguinte aluno? (1 - Sim / 2 - Não)");
        System.out.println(alunoID.toString()); 
        int op = scanner.nextInt();
        if(op != 1){
            System.out.println("Cancelando Remoção...");
            return;
        }
        turma.removeAluno(alunoID);
        sistema.getGerenciadorAlunos().removerTurmaDoAluno(id, turma);
        System.out.println("Aluno Removido com sucesso.");
    }
}