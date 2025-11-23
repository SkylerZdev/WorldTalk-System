package src.menus.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.app.ContextoSistema;
import src.gerenciadores.GerenciadorCursos;
import src.menus.admin.cursos.MenuNiveis;
import src.modelos.Curso;
import src.modelos.Nivel;

public class MenuCursos {
    Scanner scanner;
    ContextoSistema sistema;
    GerenciadorCursos gerenciador;
    public MenuCursos(Scanner scanner, ContextoSistema sistema){
        this.scanner = scanner;
        this.sistema = sistema;
        this.gerenciador = sistema.getGerenciadorCursos();
    }
    public void abrirMenu() {

        int opcao;
        do {

            System.out.println("");
            System.out.println("\n--- Gerenciamento de Cursos ---");
            System.out.println("1 - Adicionar Curso");
            System.out.println("2 - Editar Curso");
            System.out.println("3 - Remover Curso");
            System.out.println("4 - Listar Cursos");
            System.out.println("5 - Editar Niveis");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarCurso();
                    break;
                case 2:
                    if(gerenciador.getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado.");
                        break;
                    }
                    editarCurso();
                    break;
                case 3:
                    if(gerenciador.getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado.");
                        break;
                    }
                    removerCurso();
                case 4:
                    if (gerenciador.getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado");
                        break;
                    } 
                    gerenciador.listarCursos();
                    break;
                case 5:
                    if(gerenciador.getQuantidadeCursos() == 0){
                        System.out.println("Nenhum Curso Cadastrado.");
                        break;
                    }
                    gerenciador.listarCursos();
                    System.out.println("Digite o ID do curso que deseja editar os niveis");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    Curso curso = gerenciador.getCursoPorId(id);
                    if (curso == null){
                        System.out.println("Nenhum curso com esse ID encontrado.");
                        break;
                    }
                    MenuNiveis menuNiveis = new MenuNiveis(scanner, sistema, curso);
                    menuNiveis.abrirMenu();
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
    public void adicionarCurso(){
        System.out.println("Digite o Nome do Curso: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a Disciplina do Curso:");
        String disciplina = scanner.nextLine();
        System.out.println("Digite a Mensalidade do curso");
        double mensalidade = scanner.nextLong();
        scanner.nextLine();
        int num;
        int counter = 0;
        List<Nivel> listaNiveis = new ArrayList<>();
        do{
            System.out.print("Digite o Numero de Niveis que deseja criar: ");
            num = scanner.nextInt();
            scanner.nextLine();
            if(num <= 0){
                System.out.println("O Minimo de Niveis é 1, Por Favor, Tente Novamente");
                System.out.println("");
                System.out.println("");
            }
        } while (num <= 0);
        
        while(counter < num){
            System.out.print("Digite o Nome do Nivel: ");
            String nomeNivel = scanner.nextLine();
            System.out.print("Digite o Grau de Dificuldade do Nivel(1 - 10): ");
            int grau;
            do{
                grau = scanner.nextInt();
                scanner.nextLine();
                if(grau<1 || grau>10){
                    System.out.println("O Grau deve ser entre 1 a 10");
                    System.out.println("");
                    System.out.println("");
                }
            } while (grau < 1 || grau > 10);
            listaNiveis.add(new Nivel(nomeNivel, grau));
            counter++;
        }
        gerenciador.adicionarCurso(new Curso(nome, disciplina, mensalidade, listaNiveis));
        System.out.println("Curso Adicionado com Sucesso");

    }

    public void editarCurso(){
        gerenciador.listarCursos();
        System.out.println("Digite o ID do curso que deseja editar");
        long id = scanner.nextLong();
        scanner.nextLine();
        Curso curso = gerenciador.getCursoPorId(id);
        if (curso == null){
            System.out.println("Nenhum curso com esse ID encontrado.");
            return;
        }
        System.out.println("Deseja editar esse Curso? (1 - Sim / 2 - Nao)");
        System.out.println(curso.toString());
        int op = scanner.nextInt();
        scanner.nextLine();
        if (op != 1){
            System.out.println("Cancelando...");
            return;
        }
        System.out.println("Digite o novo Nome para o Curso (atual: " + curso.getNome() + "):");
        System.out.println("(Digite '-' para manter o título atual)");
        System.out.println("");
        String novoNome = scanner.nextLine();
        if (!novoNome.equals("-")) {
            curso.setNome(novoNome);
        }
        System.out.println("Digite a nova Disciplina para o Curso (atual: " + curso.getDisciplina() + "):");
        System.out.println("(Digite '-' para manter o idioma atual)");
        String novaDisciplina = scanner.nextLine();
        if (!novaDisciplina.equals("-")) {
            curso.setDisciplina(novaDisciplina);
        }
        System.out.println("Digite a nova Mensalidade para o Curso (atual: " + curso.getMensalidade() + " R$):");
        System.out.println("(Digite '-1' para manter a atual)");
        double novaMensalidade = scanner.nextDouble();
        if (novaMensalidade != (long) -1){
            curso.setMensalidade(novaMensalidade);
        }
        System.out.println("Edição Concluida");
        return; 
    }

    public void removerCurso(){
        gerenciador.listarCursos();
        System.out.println("Digite o Id do Curso que deseja Remover");
        Curso curso = gerenciador.getCursoPorId(scanner.nextLong());
        scanner.nextLine();
        if (curso == null){
            System.out.println("Nenhum Curso encontrado com esse Id");
            return;
        }
        if (!gerenciador.getTurmasPorCurso(curso).isEmpty()){
            System.out.println("Curso não pode ser removido: Existem turmas cadastradas nele.");
            return;
        }
        System.out.println("Deseja realmente remover o seguinte Curso? (1 - Sim / 2 - Não)");
        System.out.println(curso.toString());
        int op = scanner.nextInt();
        scanner.nextLine();
        if(op!= 1){
            System.out.println("Cancelando Remoção...");
            return;
        }
        gerenciador.removerCursoPorId(curso.getId());
        System.out.println("Remoção Concluida");
    }

    
}
