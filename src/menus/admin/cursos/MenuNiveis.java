package src.menus.admin.cursos;

import java.util.Scanner;
import src.app.ContextoSistema;
import src.gerenciadores.GerenciadorCursos;
import src.modelos.Curso;
import src.modelos.Nivel;

public class MenuNiveis {
    Scanner scanner;
    ContextoSistema sistema;
    GerenciadorCursos gerenciador;
    Curso curso;

    public MenuNiveis(Scanner scanner, ContextoSistema sistema, Curso curso){
        this.curso = curso;
        this.scanner = scanner;
        this.sistema = sistema;
        this.gerenciador = sistema.getGerenciadorCursos();
    }

    public void abrirMenu(){
        int op;
        do{
        System.out.println("Curso Selecionado:  " + curso.toString());
        System.out.println("---------------//---------------");
        System.out.println("-- Escolha a Opção desejada --");
        System.out.println("1 - Adicionar novo Nivel");
        System.out.println("2 - Editar Nivel Existente");
        System.out.println("3 - Remover Nivel");
        System.out.println("0 - Cancelar");
        op = scanner.nextInt();
        scanner.nextLine();
        switch(op){
            case 1:
                adicionarNivel();
                break;
            case 2:
                editarNivel();
                break;
            case 3:
                removerNivel();
                break;
            case 0:
                System.out.println("Edição Cancelada");
                break;
            default:
                System.out.println("Opção Invalida");
                break;
        
            }
        } while(op != 0);
    }

    private void adicionarNivel() {
        System.out.println("Digite o nome do nível:");
        String nome = scanner.nextLine();

        System.out.println("Digite a dificuldade do nível:");
        int dificuldade = scanner.nextInt();
        scanner.nextLine();

        Nivel novoNivel = new Nivel(nome, dificuldade);
        curso.addNivel(novoNivel);

        System.out.println("Nível adicionado com sucesso.");
    }

    private void editarNivel() {
        curso.listarNiveis(); 
        System.out.println("Digite o ID do nível que deseja editar:");
        long id = scanner.nextLong();

        Nivel nivel = curso.getNivelporID(id);
        if (nivel == null) {
            System.out.println("Nível com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Esse é o nível que você deseja editar? (1 - Sim // 2 - Não)");
        scanner.nextLine(); 
        System.out.println(nivel.toString());
        int confirmacao = scanner.nextInt();
        if (confirmacao != 1) {
            System.out.println("Edição cancelada.");
            return;
        }

        scanner.nextLine(); 
        System.out.println("Digite o novo nome do nível (atual: " + nivel.getNomeNivel() + "):");
        System.out.println("(Digite '-' para manter o nome atual)");
        String novoNome = scanner.nextLine();
        if (!novoNome.equals("-")) {
            nivel.setNomeNivel(novoNome);
        }

        System.out.println("Digite a nova dificuldade (atual: " + nivel.getDificuldade() + "):");
        System.out.println("(Digite '0' para manter a dificuldade atual)");
        int novaDificuldade = scanner.nextInt();
        scanner.nextLine();
        if (novaDificuldade != 0) {
            nivel.setDificuldade(novaDificuldade);
        }

        System.out.println("Nível editado com sucesso.");
    }

    private void removerNivel(){
        curso.listarNiveis();
        System.out.println("Digite o Id do nivel que deseja remover");
        long idNivel = scanner.nextLong();
        Nivel n = curso.getNivelporID(idNivel);
        if (n == null){
            System.out.println("Nenhum Nivel com esse Id encontrado");
            return;
        }
        //Se o Nivel ainda tiver turmas ativas, então rejeita a Remoção
        for (long idTurma : gerenciador.getTurmasPorCurso(curso)) {
            if (n.equals(sistema.getGerenciadorTurmas().getTurmaPorId(idTurma).getNivel())) {
                System.out.println("Nivel não pode ser removido: existem turmas cadastradas nele");
                return;
            }
        }
        System.out.println("Deseja realmente remover o seguinte Nivel? (1 - Sim / 2 - Não)");
        System.out.println(n.toString());
        int op = scanner.nextInt();
        if(op!= 1){
            System.out.println("Cancelando Remoção...");
            return;
        }
        curso.removerNivel(n);
        System.out.println("Nivel Removido");
    }
}