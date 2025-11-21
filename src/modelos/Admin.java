package src.modelos;
public class Admin {
    private String usuario;
    private String senha;

    public Admin(){}
    
    public Admin(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    //Getters e Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
