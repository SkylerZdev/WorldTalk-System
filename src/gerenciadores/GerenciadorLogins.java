package src.gerenciadores;

import java.util.HashMap;
import java.util.Map;

import src.exceptions.CadastroException;
import src.exceptions.LoginException;
import src.modelos.Admin;
import src.modelos.Aluno;


//Gerenciador de Logins para Admins e Alunos
public class GerenciadorLogins {
    private String senhaUniversal;
    private boolean senhaUniversalDefinida = false;
    private Map<String, Admin> admins = new HashMap<>();
    private Map<String, Aluno> alunos = new HashMap<>();

    // Define a senha universal apenas uma vez
    public void DefinirSenhaUniversal(String senha) {
        if (senhaUniversalDefinida) {
            System.out.println("Senha universal já definida.");
            return;
        }
        this.senhaUniversalDefinida = true;
        this.senhaUniversal = senha;
    }

    public boolean isSenhaUniversalDefinida() {
        return senhaUniversalDefinida;
    }

    //Cadastro e Login para Admins.
    public void CadastroAdm (String usuario, String senha, String senhaUniversal) throws CadastroException {
        if (!senhaUniversal.equals(this.senhaUniversal)) {
            throw new CadastroException("Senha universal incorreta.");
        }
        if (admins.containsKey(usuario)) {
            throw new CadastroException("Usuário já existe.");
        }
        Admin novoAdmin = new Admin(usuario, senha);
        admins.put(usuario, novoAdmin);
        return;
    }

    public Admin LoginAdm (String usuario, String senha) throws LoginException {
        if (!admins.containsKey(usuario)) {
            throw new LoginException("Usuário não encontrado.");
        }
        Admin admin = admins.get(usuario);
        if (!admin.getSenha().equals(senha)) {
            throw new LoginException("Senha incorreta..");
        }
        return admin;
    }
    
    //Cadastro e Login para Alunos
    public void CadastroAluno (Aluno aluno) throws CadastroException {
        if (alunos.containsKey(aluno.getUsuario())) {
            throw new CadastroException("Usuário já existe.");
        }
        alunos.put(aluno.getUsuario(), aluno);
        return;
    }

    public Aluno LoginAluno (String usuario, String senha) throws LoginException {
        if (!alunos.containsKey(usuario)) {
            throw new LoginException("Usuário não encontrado.");
        }
        Aluno aluno = alunos.get(usuario);
        if (!aluno.getSenha().equals(senha)) {
            throw new LoginException("Senha incorreta.");
        }
        return aluno;
    }

    // Retorna o aluno associado ao usuário
    public Aluno getAlunoPorUsuario(String usuario) {
        return alunos.get(usuario);
    }

    // Remove aluno pelo usuário
    public boolean removerAlunoPorUsuario(String usuario) {
        return alunos.remove(usuario) != null;
    }

    // Retorna o mapa de alunos
    public Map<String, Aluno> getAlunos() {
        return alunos;
    }
}