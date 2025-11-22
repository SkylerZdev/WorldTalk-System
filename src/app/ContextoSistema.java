package src.app;

import src.gerenciadores.*;

public class ContextoSistema {
    private GerenciadorAlunos gerenciadorAlunos;
    private GerenciadorBiblioteca gerenciadorBiblioteca;
    private GerenciadorCursos gerenciadorCursos;
    private GerenciadorLogins gerenciadorLogins;
    private GerenciadorProfessores gerenciadorProfessores;
    private GerenciadorEmprestimos gerenciadorEmprestimos;
    private GerenciadorTurmas gerenciadorTurmas;

    public ContextoSistema() {
        this.gerenciadorAlunos = new GerenciadorAlunos();
        this.gerenciadorBiblioteca = new GerenciadorBiblioteca();
        this.gerenciadorCursos = new GerenciadorCursos();
        this.gerenciadorLogins = new GerenciadorLogins();
        this.gerenciadorProfessores = new GerenciadorProfessores();
        this.gerenciadorEmprestimos = new GerenciadorEmprestimos();
        this.gerenciadorTurmas = new GerenciadorTurmas();
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

}
