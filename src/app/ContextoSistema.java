package src.app;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import src.exceptions.CadastroException;
import src.gerenciadores.*;
import src.modelos.Aluno;
import src.modelos.Curso;
import src.modelos.Nivel;
import src.modelos.Professor;

public class ContextoSistema {
    private GerenciadorAlunos gerenciadorAlunos;
    private GerenciadorBiblioteca gerenciadorBiblioteca;
    private GerenciadorCursos gerenciadorCursos;
    private GerenciadorLogins gerenciadorLogins;
    private GerenciadorProfessores gerenciadorProfessores;
    private GerenciadorEmprestimos gerenciadorEmprestimos;
    private GerenciadorTurmas gerenciadorTurmas;
    private GerenciadorInscricoes gerenciadorInscricoes;

    public ContextoSistema() {
        this.gerenciadorAlunos = new GerenciadorAlunos();
        this.gerenciadorBiblioteca = new GerenciadorBiblioteca();
        this.gerenciadorCursos = new GerenciadorCursos();
        this.gerenciadorLogins = new GerenciadorLogins();
        this.gerenciadorProfessores = new GerenciadorProfessores();
        this.gerenciadorEmprestimos = new GerenciadorEmprestimos();
        this.gerenciadorTurmas = new GerenciadorTurmas();
        this.gerenciadorInscricoes = new GerenciadorInscricoes();
    }
    
    public void carregarDadosDeTeste(ContextoSistema sistema) {

        GerenciadorAlunos gAlunos = sistema.getGerenciadorAlunos();
        GerenciadorLogins gLogins = sistema.getGerenciadorLogins();
        GerenciadorCursos gCursos = sistema.getGerenciadorCursos();
        GerenciadorProfessores gProfessores = sistema.getGerenciadorProfessores();

        //Alunos
        Aluno a1 = new Aluno("Ana Bezerra", "ana.b", "123");
        Aluno a2 = new Aluno("Bruno Costa", "bruno.c", "123");
        Aluno a3 = new Aluno("Carla Dias", "carla.d", "123");
        Aluno a4 = new Aluno("Diego Lima", "diego.l", "123");
        Aluno a5 = new Aluno("Eduarda Melo", "duda.m", "123");

        Aluno a6 = new Aluno("Felipe Nogueira", "felipe.n", "123");
        a6.setVip(true);

        Aluno a7 = new Aluno("Gabriela Oliveira", "gabi.o", "123");
        a7.setVip(true);

        Aluno a8 = new Aluno("Henrique Pereira", "henrique.p", "123");
        a8.setVip(true);

        Aluno a9 = new Aluno("Isabela Queiroz", "isa.q", "123");
        a9.setVip(true);

        Aluno a10 = new Aluno("João Ricardo", "joao.r", "123");
        a10.setVip(true);

        Aluno a11 = new Aluno("Larissa Monteiro", "larissa.m", "123");
        a11.setVip(true);

        Aluno a12 = new Aluno("Matheus Andrade", "matheus.a", "123");
            
        Aluno a13 = new Aluno("Carolina Farias", "carol.f", "123");
        a13.setVip(true);
            
        Aluno a14 = new Aluno("Rafael Nogueira", "rafael.n", "123");
            
        Aluno a15 = new Aluno("Beatriz Sampaio", "bea.s", "123");
        a15.setVip(true);
            
        Aluno a16 = new Aluno("Gustavo Barros", "gustavo.b", "123");
            
        Aluno a17 = new Aluno("Maria Eduarda Lopes", "madu.l", "123");
        a17.setVip(true);
            
        Aluno a18 = new Aluno("Igor Fernandes", "igor.f", "123");
            
        Aluno a19 = new Aluno("Camila Duarte", "camila.d", "123");
            
        Aluno a20 = new Aluno("Thiago Oliveira", "thiago.o", "123");
        a20.setVip(true);
            
        Aluno a21 = new Aluno("Ana Julia Correia", "anaj.c", "123");
            
        Aluno a22 = new Aluno("Fábio Torres", "fabio.t", "123");
        a22.setVip(true);
            
        Aluno a23 = new Aluno("Paula Freitas", "paula.f", "123");
            
        Aluno a24 = new Aluno("Bruno Cardoso", "bruno.c", "123");
        a24.setVip(true);
            
        Aluno a25 = new Aluno("Natália Ribeiro", "natalia.r", "123");
        registrarAlunoDeTeste(gAlunos, gLogins, a1);
        registrarAlunoDeTeste(gAlunos, gLogins, a2);
        registrarAlunoDeTeste(gAlunos, gLogins, a3);
        registrarAlunoDeTeste(gAlunos, gLogins, a4);
        registrarAlunoDeTeste(gAlunos, gLogins, a5);
        registrarAlunoDeTeste(gAlunos, gLogins, a6);
        registrarAlunoDeTeste(gAlunos, gLogins, a7);
        registrarAlunoDeTeste(gAlunos, gLogins, a8);
        registrarAlunoDeTeste(gAlunos, gLogins, a9);
        registrarAlunoDeTeste(gAlunos, gLogins, a10);
        registrarAlunoDeTeste(gAlunos, gLogins, a11);
        registrarAlunoDeTeste(gAlunos, gLogins, a12);
        registrarAlunoDeTeste(gAlunos, gLogins, a13);
        registrarAlunoDeTeste(gAlunos, gLogins, a14);
        registrarAlunoDeTeste(gAlunos, gLogins, a15);
        registrarAlunoDeTeste(gAlunos, gLogins, a16);
        registrarAlunoDeTeste(gAlunos, gLogins, a17);
        registrarAlunoDeTeste(gAlunos, gLogins, a18);
        registrarAlunoDeTeste(gAlunos, gLogins, a19);
        registrarAlunoDeTeste(gAlunos, gLogins, a20);
        registrarAlunoDeTeste(gAlunos, gLogins, a21);
        registrarAlunoDeTeste(gAlunos, gLogins, a22);
        registrarAlunoDeTeste(gAlunos, gLogins, a23);
        registrarAlunoDeTeste(gAlunos, gLogins, a24);
        registrarAlunoDeTeste(gAlunos, gLogins, a25);

        List<Nivel> niveisIngles = Arrays.asList(
            new Nivel("Iniciante", 1),
            new Nivel("Intermediário", 2)
        );
        registrarCursoDeTeste(gCursos, "Inglês Completo", "Inglês", 450.0, niveisIngles);

        // ===== CURSO 2 =====
        List<Nivel> niveisEspanhol = Arrays.asList(
            new Nivel("Básico", 1),
            new Nivel("Avançado", 3)
        );
        registrarCursoDeTeste(gCursos, "Espanhol Intensivo", "Espanhol", 420.0, niveisEspanhol);

        // ===== CURSO 3 =====
        List<Nivel> niveisFrances = Arrays.asList(
            new Nivel("Conversação", 2),
            new Nivel("Profissional", 4)
        );
        registrarCursoDeTeste(gCursos, "Francês Profissional", "Francês", 500.0, niveisFrances);

        // ===== CURSO 4 =====
        List<Nivel> niveisAlemao = Arrays.asList(
            new Nivel("A1", 1),
            new Nivel("B1", 2)
        );
        registrarCursoDeTeste(gCursos, "Alemão Essencial", "Alemão", 530.0, niveisAlemao);

        // ===== CURSO 5 =====
        List<Nivel> niveisJapones = Arrays.asList(
            new Nivel("Hiragana & Katakana", 1),
            new Nivel("Kanji Básico", 2)
        );
        registrarCursoDeTeste(gCursos, "Japonês Fundamental", "Japonês", 600.0, niveisJapones);

        // ===== CURSO 6 =====
        List<Nivel> niveisItaliano = Arrays.asList(
            new Nivel("Básico", 1),
            new Nivel("Intermediário", 2)
        );
        registrarCursoDeTeste(gCursos, "Italiano Para Viagem", "Italiano", 380.0, niveisItaliano);

        // ===== CURSO 7 =====
        List<Nivel> niveisMandarim = Arrays.asList(
            new Nivel("Pinyin", 1),
            new Nivel("Mandarim I", 2)
        );
        registrarCursoDeTeste(gCursos, "Mandarim Inicial", "Mandarim", 650.0, niveisMandarim);

        // ===== CURSO 8 =====
        List<Nivel> niveisRusso = Arrays.asList(
            new Nivel("Escrita Cirílica", 1),
            new Nivel("Conversação Básica", 2)
        );
        registrarCursoDeTeste(gCursos, "Russo Básico", "Russo", 550.0, niveisRusso);

        // ===== CURSO 9 =====
        List<Nivel> niveisPortugues = Arrays.asList(
            new Nivel("Gramática", 1),
            new Nivel("Redação", 2)
        );
        registrarCursoDeTeste(gCursos, "Português Avançado", "Português", 300.0, niveisPortugues);

        // ===== CURSO 10 =====
        List<Nivel> niveisCoreano = Arrays.asList(
            new Nivel("Hangul", 1),
            new Nivel("Conversação", 2)
        );
        registrarCursoDeTeste(gCursos, "Coreano Moderno", "Coreano", 580.0, niveisCoreano);

        Professor p1 = new Professor("Mariana Lopes", Set.of(
            gCursos.getCursoPorId(1),
            gCursos.getCursoPorId(3)
        ));
        Professor p2 = new Professor("Rodrigo Almeida", Set.of(
            gCursos.getCursoPorId(2),
            gCursos.getCursoPorId(4),
            gCursos.getCursoPorId(6)
        ));
        Professor p3 = new Professor("Fernanda Ribeiro", Set.of(
            gCursos.getCursoPorId(1),
            gCursos.getCursoPorId(5)
        ));
        Professor p4 = new Professor("Gustavo Soares", Set.of(
            gCursos.getCursoPorId(3),
            gCursos.getCursoPorId(7),
            gCursos.getCursoPorId(8)
        ));
        Professor p5 = new Professor("Beatriz Azevedo", Set.of(
            gCursos.getCursoPorId(4),
            gCursos.getCursoPorId(9)
        ));
        Professor p6 = new Professor("Lucas Ferreira", Set.of(
            gCursos.getCursoPorId(5),
            gCursos.getCursoPorId(10)
        ));
        Professor p7 = new Professor("Patrícia Monteiro", Set.of(
            gCursos.getCursoPorId(2),
            gCursos.getCursoPorId(6),
            gCursos.getCursoPorId(8)
        ));
        Professor p8 = new Professor("Thiago Martins", Set.of(
            gCursos.getCursoPorId(1),
            gCursos.getCursoPorId(9),
            gCursos.getCursoPorId(10)
        ));
        Professor p9 = new Professor("Aline Fernandes", Set.of(
            gCursos.getCursoPorId(3),
            gCursos.getCursoPorId(4)
        ));
        Professor p10 = new Professor("Eduardo Castro", Set.of(
            gCursos.getCursoPorId(7),
            gCursos.getCursoPorId(8),
            gCursos.getCursoPorId(10)
        ));
        registrarProfessorDeTeste(gProfessores, p1);
        registrarProfessorDeTeste(gProfessores, p2);
        registrarProfessorDeTeste(gProfessores, p3);
        registrarProfessorDeTeste(gProfessores, p4);
        registrarProfessorDeTeste(gProfessores, p5);
        registrarProfessorDeTeste(gProfessores, p6);
        registrarProfessorDeTeste(gProfessores, p7);
        registrarProfessorDeTeste(gProfessores, p8);
        registrarProfessorDeTeste(gProfessores, p9);
        registrarProfessorDeTeste(gProfessores, p10);

    }

    private void registrarAlunoDeTeste(GerenciadorAlunos gAlunos,
                                       GerenciadorLogins gLogins,
                                       Aluno aluno) {
        try {
            gLogins.CadastroAluno(aluno);
        } catch (CadastroException e) {
            System.out.println("Erro ao cadastrar login do aluno " + aluno.getNome() + ": " + e.getMessage());
        }

        gAlunos.adicionarAluno(aluno);
    }
    private void registrarCursoDeTeste(GerenciadorCursos gCursos,
                                   String nomeCurso,
                                   String disciplina,
                                   double mensalidade,
                                   List<Nivel> niveis) {

        Curso c = new Curso(nomeCurso, disciplina, mensalidade, niveis);

        // Registrar curso no gerenciador
        gCursos.adicionarCurso(c);
    }

    private void registrarProfessorDeTeste(GerenciadorProfessores gProfessores, Professor professor) {
        gProfessores.adicionarProfessor(professor);
    }

    
    public GerenciadorAlunos getGerenciadorAlunos() {
        return gerenciadorAlunos;
    }

    public GerenciadorBiblioteca getGerenciadorBiblioteca() {
        return gerenciadorBiblioteca;
    }

    public GerenciadorCursos getGerenciadorCursos() {
        return gerenciadorCursos;
    }

    public GerenciadorLogins getGerenciadorLogins() {
        return gerenciadorLogins;
    }

    public GerenciadorProfessores getGerenciadorProfessores() {
        return gerenciadorProfessores;
    }

    public GerenciadorEmprestimos getGerenciadorEmprestimos() {
        return gerenciadorEmprestimos;
    }

    public GerenciadorTurmas getGerenciadorTurmas() {
        return gerenciadorTurmas;
    }

    public GerenciadorInscricoes getGerenciadorInscricoes(){
        return gerenciadorInscricoes;
    }
}
