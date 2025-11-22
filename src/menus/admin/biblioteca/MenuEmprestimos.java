package src.menus.admin.biblioteca;
import java.util.Scanner;
import java.util.List;
import src.app.ContextoSistema;
import src.modelos.*;

public class MenuEmprestimos {
    private ContextoSistema sistema;
    private Scanner scanner;

    public MenuEmprestimos(ContextoSistema sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
    }

    public void abrirMenu() {

        int opcao;
        do {
            System.out.println("");
            System.out.println("\n--- Gerenciar Emprestimos ---");
            System.out.println("1 - Gerar Empréstimo");
            System.out.println("2 - Listar Empréstimos Ativos");
            System.out.println("3 - Checar Histórico de Empréstimos");
            System.out.println("4 - Confirmar Devolução");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerarEmprestimo();
                    break;
                case 2:
                    listarEmprestimosAtivos();
                    break;
                case 3:
                    checarHistoricoEmprestimos();
                    break;
                case 4:
                    confirmarDevolucao();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void gerarEmprestimo() {
        if (sistema.getGerenciadorBiblioteca().getQuantidadeMateriaisDisponiveis() == 0) {
            System.out.println("Nenhum material disponível para empréstimo.");
            return;
            }
            sistema.getGerenciadorBiblioteca().ListarMateriaisDisponiveis();
            System.out.println("Digite o ID do material que deseja emprestar:");
            long idMaterial = scanner.nextLong();
            MaterialBB material = sistema.getGerenciadorBiblioteca().getMaterialPorId(idMaterial);
            if (material == null || !material.isDisponivel()) {
                System.out.println("Material indisponível para empréstimo.");
                return;
            }
            System.out.println(material.getTitulo() + " selecionado para empréstimo.");
            scanner.nextLine(); // Consumir a nova linha
            if(material.isExclusivoVip()){
                sistema.getGerenciadorAlunos().listarAlunosVip();
            }else{
                sistema.getGerenciadorAlunos().listarAlunos();
            }
            System.out.println("Digte o id do aluno que fará o empréstimo:");
            Aluno aluno = sistema.getGerenciadorAlunos().getAlunoPorId(scanner.nextLong());
            if (aluno == null) {
                System.out.println("Aluno não encontrado.");
                return;
            }
            if(material.isExclusivoVip() && !aluno.isVip()) {
                System.out.println("Empréstimo negado. O material é exclusivo para alunos VIP.");
                return;
            }
            Emprestimo emprestimo = new Emprestimo(material, aluno);
            if(sistema.getGerenciadorEmprestimos().adicionarEmprestimo(emprestimo)){
                System.out.println("Emprestimo Gerado");
            }

    }

    private void listarEmprestimosAtivos() {
        if(sistema.getGerenciadorEmprestimos().getQuantidadeEmprestimosAtivos() == 0){
            System.out.println("Nenhum Emprestimo Ativo");
        }
        for (Emprestimo e : sistema.getGerenciadorEmprestimos().getEmprestimosAtivos()) {
            System.out.println(e.toString());
        }
    }

    private void checarHistoricoEmprestimos() {
            System.out.println("Historico de Emprestimos");
            System.out.println("1 - Historico de Aluno");
            System.out.println("2 - Historico de Material");
            int opcao = scanner.nextInt();
            switch(opcao){
                case 1:
                    sistema.getGerenciadorAlunos().listarAlunos();
                    System.out.println("Digite o Id de um aluno");
                    Aluno aluno = sistema.getGerenciadorAlunos().getAlunoPorId(scanner.nextLong());
                    if (aluno == null){
                        System.out.println("Aluno não encontrado");
                        return;
                    }
                    List<Emprestimo> historicoAluno = sistema.getGerenciadorEmprestimos().getHistoricoAluno(aluno);
                    if(historicoAluno.isEmpty()){
                        System.out.println("Aluno não tem Emprestimos Registrados");
                        return;
                    } else{
                        for (Emprestimo emprestimo : historicoAluno) {
                            System.out.println(emprestimo.toString());
                        }
                    }
                    break;
                case 2:
                    sistema.getGerenciadorBiblioteca().ListarMateriais();
                    System.out.println("Digite o Id de um Material");
                    MaterialBB material = sistema.getGerenciadorBiblioteca().getMaterialPorId(scanner.nextLong());
                    if (material == null){
                        System.out.println("Material não encontrado");
                        return;
                    }
                    List<Emprestimo> historicoMaterial = sistema.getGerenciadorEmprestimos().getHistoricoMaterial(material);
                    if(historicoMaterial.isEmpty()){
                        System.out.println("Material não tem Emprestimos Registrados");
                        return;
                    } else{
                        for (Emprestimo emprestimo : historicoMaterial) {
                            System.out.println(emprestimo.toString());
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
    }

    private void confirmarDevolucao() {
        if(sistema.getGerenciadorEmprestimos().getQuantidadeEmprestimosAtivos()==0){
            System.out.println("Nenhum Emprestimo Pendente");
            return;
        }
        sistema.getGerenciadorEmprestimos().listarEmprestimosAtivos();
        System.out.println("Digite o Id de qual Emprestimo deseja Confirmar");
        Emprestimo e = sistema.getGerenciadorEmprestimos().getEmprestimoPorId(scanner.nextLong());
        if (e == null){
            System.out.println("Emprestimo nao encontrado");
            return;
        }
        System.out.println(e.toString());
        System.out.println("Deseja confirmar a devolucao desse emprestimo? (1 - Sim / 2 - Nao)");
        int opcao = scanner.nextInt();
        if (opcao != 1){
            System.out.println("Confirmacao Cancelada");
            return;
        }
        sistema.getGerenciadorEmprestimos().concluirEmprestimo(e.getId());
        System.out.println("Devolucao Confirmada.");
    }
}
