import java.util.HashMap;
import java.util.Map;


//Gerenciador de Logins para Admins e Alunos
public class GerenciadorLogins {
    String senhaUniversal;
    Map<String, Admin> admins = new HashMap<>();
    Map<String, Aluno> alunos = new HashMap<>();

    // Define a senha universal apenas uma vez
    public void DefinirSenhaUniversal(String senha) {
        if (senhaUniversal != null) {
            System.out.println("Senha universal já definida.");
            return;
        }
        this.senhaUniversal = senha;
    }

    //Cadastro e Login para Admins.
    public void CadastroAdm (String usuario, String senha, String senhaUniversal) throws CadastroException {
        if (this.senhaUniversal == null) {
            throw new CadastroException("Senha universal não definida.");
        }
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
    public void CadastroAluno (String usuario, String senha) throws CadastroException {
        if (alunos.containsKey(usuario)) {
            throw new CadastroException("Usuário já existe.");
        }
        Aluno novoAluno = new Aluno(usuario, senha);
        alunos.put(usuario, novoAluno);
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
    
}
