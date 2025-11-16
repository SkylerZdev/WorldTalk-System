import java.util.HashMap;
import java.util.Map;

public class GerenciadorLogins {
    String senhaUniversal;
    Map<String, Admin> admins = new HashMap<>();
    Map<String, Aluno> alunos = new HashMap<>();

    public void DefinirSenhaUniversal(String senha) {
        if (senhaUniversal != null) {
            System.out.println("Senha universal já definida.");
            return;
        }
        this.senhaUniversal = senha;
    }

    public int CadastroAdm (String usuario, String senha, String senhaUniversal) {
        if (senhaUniversal == null) {
            System.out.println("Senha universal não definida. Não é possível cadastrar administrador.");
            return 0;
        }
        if (!senhaUniversal.equals(this.senhaUniversal)) {
            System.out.println("Senha universal incorreta. Cadastro de administrador falhou.");
            return 0;
        }
        if (admins.containsKey(usuario)) {
            System.out.println("Usuário administrador já existe.");
            return 0;
        }
        Admin novoAdmin = new Admin(usuario, senha);
        admins.put(usuario, novoAdmin);
        return 1;
    }

    public Admin LoginAdm (String usuario, String senha) throws LoginException {
        if (!admins.containsKey(usuario)) {
            throw new LoginException("Usuário administrador não encontrado.");
        }
        Admin admin = admins.get(usuario);
        if (!admin.getSenha().equals(senha)) {
            throw new LoginException("Senha incorreta para o usuário administrador.");
        }
        return admin;
    }

    public int CadastroAluno (String usuario, String senha) {
        if (alunos.containsKey(usuario)) {
            System.out.println("Usuário aluno já existe.");
            return 0;
        }
        Aluno novoAluno = new Aluno(usuario, senha);
        alunos.put(usuario, novoAluno);
        return 1;
    }

    public Aluno LoginAluno (String usuario, String senha) throws LoginException {
        if (!alunos.containsKey(usuario)) {
            throw new LoginException("Usuário aluno não encontrado.");
        }
        Aluno aluno = alunos.get(usuario);
        if (!aluno.getSenha().equals(senha)) {
            throw new LoginException("Senha incorreta para o usuário aluno.");
        }
        return aluno;
    }
    
}
