package src.app;
import java.util.Scanner;
import src.menus.principal.MenuPrincipal;
import src.modelos.Aluno;
import src.modelos.MaterialBB;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContextoSistema sistema = new ContextoSistema();
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Arthur Mendes", "senha123"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Bianca Alves", "b1234"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Caio Silva", "caio_senha"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Daniela Rocha", "dani2024"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Eduardo F.", "ed_pass"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Fernanda Costa", "fc_2025"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Gabriel Martins", "g@briel"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Helena Pereira", "helena!pw"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Igor Tavares", "it_88"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Juliana S.", "ju_senha"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Kevin Duarte", "k3v1n"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Larissa Monte", "lari_pw"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Marcos Vinícius", "marcos123"));
        sistema.getGerenciadorAlunos().adicionarAluno(new Aluno("Nicole Ramos", "nic0l3"));

        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Inglês Básico - Livro 1", "Inglês", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Inglês Intermediário - Workbook", "Inglês", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Francês para Iniciantes", "Francês", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Gramática Francesa Avançada", "Francês", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Espanhol Rápido - Guia de Conversação", "Espanhol", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Espanhol Completo", "Espanhol", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Alemão Essencial", "Alemão", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Alemão Intensivo - Exercícios", "Alemão", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Japonês Hiragana e Katakana", "Japonês", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Kanji 500 – Volume 1", "Japonês", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Italiano Básico", "Italiano", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Italiano Avançado - Conversação", "Italiano", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Português para Estrangeiros", "Português", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Mandarim Passo a Passo", "Mandarim", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Mandarim – Leitura Avançada", "Mandarim", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Coreano K-Level 1", "Coreano", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Coreano K-Level 2", "Coreano", true));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Árabe - Introdução", "Árabe", false));
        sistema.getGerenciadorBiblioteca().adicionarMaterial(new MaterialBB("Árabe - Vocabulário Prático", "Árabe", true));
        
        menuPrincipal.abrirMenu(scanner, sistema);
        scanner.close();
    }

}
