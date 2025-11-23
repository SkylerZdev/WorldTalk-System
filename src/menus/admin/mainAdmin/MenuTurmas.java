package src.menus.admin.mainAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import src.agenda.*;
import src.app.ContextoSistema;
import src.menus.admin.turmas.MenuAlunosTurma;
import src.gerenciadores.*;
import src.modelos.*;

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
            System.out.println("2 - Ajustar Capacidade de Turma");
            System.out.println("3 - Listar Turma");
            System.out.println("4 - Remover Turma");
            System.out.println("5 - Gerenciar Alunos de Turma");
            System.out.println("6 - Consultar Agendas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarTurma();
                    break;
                case 2:
                    editarTurma();                    
                    break;
                case 3:
                    if (gerenciador.getQuantidadeTurmas() == 0){
                        System.out.println("Nenhuma Turma Cadastrada");
                        break;
                    } else {
                        gerenciador.listarTurmas();
                    }
                    break;
                case 4:
                    removerTurma();
                    break;
                case 5:
                    if (gerenciador.getQuantidadeTurmas() == 0){
                        System.out.println("Nenhuma Turma Cadastrada");
                        break;
                    } else {
                        gerenciador.listarTurmas();
                    }
                    System.out.println("Digite o ID da turma que deseja gerenciar ");
                    Turma turma = gerenciador.getTurmaPorId(scanner.nextLong());
                    if (turma == null){
                        System.out.println("Nenhuma turma encontrada com esse ID");
                        break;
                    }
                    MenuAlunosTurma menuAlunosTurma = new MenuAlunosTurma(sistema, scanner, turma);
                    menuAlunosTurma.abrirMenu();
                    break;
                case 6:
                    exibirAgenda();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarTurma() {
        GerenciadorProfessores gProfessores = sistema.getGerenciadorProfessores();
        GerenciadorCursos gCursos = sistema.getGerenciadorCursos();

        gCursos.listarCursos();
        System.out.println("Digite o ID do curso desejado:");
        long idCurso = scanner.nextLong();
        scanner.nextLine(); 

        Curso curso = gCursos.getCursoPorId(idCurso);
        if (curso == null) {
            System.out.println("Curso com ID " + idCurso + " não encontrado.");
            return;
        }

        curso.listarNiveis();
        System.out.println("Digite o ID do nível desejado:");
        long idNivel = scanner.nextLong();
        scanner.nextLine();

        Nivel nivel = curso.getNivelporID(idNivel);
        if (nivel == null) {
            System.out.println("Nível com ID " + idNivel + " não encontrado.");
            return;
        }

        System.out.println("Digite o limite de alunos da turma:");
        int limiteAlunos = scanner.nextInt();
        scanner.nextLine();
        Agenda agenda = gerarAgenda();
        List<Professor> habilitados = gProfessores.getProfessoresHabilitados(curso);
        List<Professor> compativeis = new ArrayList<>();
        for (Professor professor : habilitados) {
            if(agenda.isCompativelCom(professor.getAgenda())){
                compativeis.add(professor);
            }
        }
        if(compativeis.isEmpty()){
            System.out.println("Nenhum Professor Disponivel para Gerenciar a Turma");
            return;
        }
        System.out.println("Professores de " + curso.getDisciplina() + " Disponiveis:");
        for (Professor professor : compativeis) {
            System.out.println(professor.toString());
        }
        System.out.print("Digite o ID do Professor responsável pela Turma: ");
        Professor prof = gProfessores.getProfessorPorId(scanner.nextLong());
        if (prof == null){
            System.out.println("Nenhum professor identificado com esse ID");
            return;
        }
        gerenciador.adicionarTurma(new Turma(curso, nivel, prof, limiteAlunos, agenda));
        System.out.println("Turma Cadastrada com Sucesso.");


    }

    private void editarTurma(){
        if (gerenciador.getQuantidadeTurmas() == 0){
            System.out.println("Nenhuma Turma Cadastrada");
            return;
        } else {
            gerenciador.listarTurmas();
        }
        System.out.println("Digite o ID da turma que deseja editar ");
        Turma turma = gerenciador.getTurmaPorId(scanner.nextLong());
        if (turma == null){
            System.out.println("Nenhuma turma encontrada com esse ID");
            return;
        }
        System.out.println("Insira o novo limite maximo de Alunos (Atual: " + turma.getLimiteAlunos() + ")");
        int novoLimite = scanner.nextInt();
        scanner.nextLine();
        if (novoLimite<turma.getQuantidadeAlunos()){
            System.out.println("A Quantidade de alunos na turma excede seu novo limite");
            System.out.println("Cancelando Edição...");
            return;
        }
        turma.setLimiteAlunos(novoLimite);
    }

    private void removerTurma(){
        if (gerenciador.getQuantidadeTurmas() == 0){
            System.out.println("Nenhuma Turma Cadastrada");
            return;
        } else {
            gerenciador.listarTurmas();
        }
        System.out.println("Digite o ID da turma que deseja gerenciar ");
        Turma turma = gerenciador.getTurmaPorId(scanner.nextLong());
        if (turma == null){
            System.out.println("Nenhuma turma encontrada com esse ID");
            return;
        }
        if (turma.getQuantidadeAlunos() != 0){
            System.out.println("Você não pode remover uma turma com Alunos registrados");
            return;
        }
        Agenda agendaProf = turma.getProfessor().getAgenda();
        Set<Horario> conflitantes = turma.getAgenda().listarConflitosCom(agendaProf);
        for (DiaSemana dia : DiaSemana.values()) {
            agendaProf.liberarHorarios(dia, conflitantes);
        }
        gerenciador.removerTurmaPorId(turma.getId());
        System.out.println("Turma Removida com Sucesso");
    }

    private void exibirAgenda(){
        if (gerenciador.getQuantidadeTurmas() == 0){
            System.out.println("Nenhuma Turma Cadastrada");
            return;
        } else {
            gerenciador.listarTurmas();
        }
        System.out.println("Digite o ID da turma que deseja gerenciar ");
        Turma turma = gerenciador.getTurmaPorId(scanner.nextLong());
        if (turma == null){
            System.out.println("Nenhuma turma encontrada com esse ID");
            return;
        }
        turma.exibirAgenda();
        pausar();
    }

    private Agenda gerarAgenda(){
        Agenda a = new Agenda();
        System.out.println("\n===== GERADOR DE AGENDA =====");
        int id;
        for (DiaSemana dia : DiaSemana.values()) {
            do{
                a.exibirAgendaDoDia(dia);
                System.out.println("Digite o ID do Horario que deseja Ocupar/Liberar");
                System.out.println("Aperte '0' para ir ao dia seguinte.");
                id = scanner.nextInt();
                scanner.nextLine();
                switch (id){
                    case 1:
                        if(!a.ocuparHorario(dia, HorarioPermitido.H8_10)){
                            a.liberarHorario(dia, HorarioPermitido.H8_10);
                        }
                        break;
                    case 2:
                        if (!a.ocuparHorario(dia, HorarioPermitido.H10_12)) {
                            a.liberarHorario(dia, HorarioPermitido.H10_12);
                            }
                        break;
                    case 3:
                        if (!a.ocuparHorario(dia, HorarioPermitido.H14_16)) {
                            a.liberarHorario(dia, HorarioPermitido.H14_16);
                            }
                        break;
                    case 4:
                        if (!a.ocuparHorario(dia, HorarioPermitido.H16_18)) {
                            a.liberarHorario(dia, HorarioPermitido.H16_18);
                            }
                        break;
                    case 5:
                        if (!a.ocuparHorario(dia, HorarioPermitido.H18_20)) {
                            a.liberarHorario(dia, HorarioPermitido.H18_20);
                            }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção Invalida, Tente Novamente.");
                        break;
                }
            } while (id != 0);
        }
        return a;
    }
    

    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}