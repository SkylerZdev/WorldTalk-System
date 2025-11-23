package src.modelos;

public class Aluno {

    private String nome;
    private String usuario;
    private final long id;
    private String senha;
    private boolean isVip;

    private static long idCounter = 1; // Gerador de IDs únicos

    public Aluno() {
        this.id = idCounter++;
    }

    public Aluno(String nome, String usuario, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.id = idCounter++;
        this.isVip = false;
    }

    // ------------------ GETTERS E SETTERS ------------------

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String user){
        this.usuario = user;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public long getId() {
        return id;
    }

    // ------------------ REPRESENTAÇÃO DO OBJETO ------------------

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + (isVip ? " (VIP)" : "");
    }
}