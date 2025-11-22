package src.modelos;
public class MaterialBB {
    private String titulo;
    private String idioma;
    private boolean exclusivoVip;
    private boolean isDisponivel;
    private boolean ativo;
    private final long idMaterial;
    private static long idCounter = 1;

    public MaterialBB(String titulo, String idioma, boolean exclusivoVip) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.exclusivoVip = exclusivoVip;
        this.isDisponivel = true;
        this.idMaterial = idCounter++;
        this.ativo = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public long getIdMaterial() {
        return idMaterial;
    }

    public boolean isExclusivoVip() {
        return exclusivoVip;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDisponivel(boolean disponivel) {
        isDisponivel = disponivel;
    }

    public void setExclusivoVip(boolean exclusivoVip) {
        this.exclusivoVip = exclusivoVip;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Material--> " +
                "ID = " + idMaterial +
                ", Título = '" + titulo + '\'' +
                ", Idioma = '" + idioma + '\'' +
                ", Exclusivo VIP = " + exclusivoVip +
                ", Disponível = " + isDisponivel +
                '}';
    }
}
