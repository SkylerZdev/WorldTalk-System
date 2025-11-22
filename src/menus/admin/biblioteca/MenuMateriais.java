package src.menus.admin.biblioteca;

import java.util.Scanner;

import src.app.ContextoSistema;
import src.modelos.*;

public class MenuMateriais {
    
    private ContextoSistema sistema;
    private Scanner scanner;

    public MenuMateriais(ContextoSistema sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
    }

    public void abrirMenu() {

        int opcao;
        do {
            System.out.println("");
            System.out.println("\n--- Gerenciar Materiais ---");
            System.out.println("1 - Adicionar Material");
            System.out.println("2 - Editar Material");
            System.out.println("3 - Remover Material");
            System.out.println("4 - Listar Materiais");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarMaterial();
                    break;
                case 2:
                    editarMaterial();
                    break;
                case 3:
                    removerMaterial();
                    break;
                case 4:
                    if (sistema.getGerenciadorBiblioteca().getQuantidadeMateriais()==0) {
                        System.out.println("Nenhum material cadastrado.");
                        break;
                    }
                    sistema.getGerenciadorBiblioteca().ListarMateriais();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void adicionarMaterial() {
        scanner.nextLine();
        System.out.println("Digite o título do material:");
        String titulo = scanner.nextLine();
        System.out.println("Digite o idioma do material:");
        String idioma = scanner.nextLine();
        System.out.println("O material é exclusivo para VIPs? (1 - Sim // 2 - Não)");
        int exclusivoVipInput = scanner.nextInt();
        boolean exclusivoVip = (exclusivoVipInput == 1);
        MaterialBB novoMaterial = new MaterialBB(titulo, idioma, exclusivoVip);
        sistema.getGerenciadorBiblioteca().adicionarMaterial(novoMaterial);
        System.out.println("Material adicionado com sucesso.");
    }

    private void editarMaterial() {
        sistema.getGerenciadorBiblioteca().ListarMateriais();
        System.out.println("Digite o ID do material que deseja editar:");
        long id = scanner.nextLong();
        MaterialBB material = sistema.getGerenciadorBiblioteca().getMaterialPorId(id);
        if (material == null) {
            System.out.println("Material com ID " + id + " não encontrado.");
            return;
        }
        
        System.out.println("Esse é o material que você deseja editar? (1 - Sim // 2 - Nao)");
        scanner.nextLine(); 
        System.out.println(material.toString());
        int confirmacao = scanner.nextInt();
        if (confirmacao != 1) {
            System.out.println("Edição cancelada.");
            return;
        }
        scanner.nextLine();
        System.out.println("Digite o novo título do material (atual: " + material.getTitulo() + "):");
        System.out.println("(Digite '-' para manter o título atual)");
        System.out.println("");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.equals("-")) {
            material.setTitulo(novoTitulo);
        }
        System.out.println("Digite o novo idioma do material (atual: " + material.getIdioma() + "):");
        System.out.println("(Digite '-' para manter o idioma atual)");
        String novoIdioma = scanner.nextLine();
        if (!novoIdioma.equals("-")) {
            material.setIdioma(novoIdioma);
        }

        System.out.println("O material é exclusivo para VIPs? (1 - Sim // 2 - Não // 3 - Manter atual)");
        int exclusivoVipInput = scanner.nextInt();
        if (exclusivoVipInput == 1) {
            material.setExclusivoVip(true);
        } else if (exclusivoVipInput == 2) {
            material.setExclusivoVip(false);
        } // Se for 3, mantém o valor atual
        System.out.println("Material editado com sucesso.");

    }

    private void removerMaterial() {
        sistema.getGerenciadorBiblioteca().ListarMateriais();
        System.out.println("Digite o ID do material que deseja remover:");
        long id = scanner.nextLong();
        MaterialBB material = sistema.getGerenciadorBiblioteca().getMaterialPorId(id);
        if (material == null) {
            System.out.println("Material com ID " + id + " não encontrado.");
            return;
        }
        if (!material.isDisponivel()) {
            System.out.println("Material com ID " + id + " não pode ser removido pois está emprestado.");
            return;
        }
        System.out.println("Deseja realmente remover o seguinte material? (1 - Sim// 2 - Nao)");
        System.out.println(material.toString());
        int confirmacao = scanner.nextInt();
        if (confirmacao == 1) {
            sistema.getGerenciadorBiblioteca().removerMaterialPorId(id);
            System.out.println("Material removido com sucesso.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

}
