package src.modelos;
public class MaterialBB {
    private String titulo;
    private String idioma;
    private boolean exclusivoVip;
    private boolean isDisponivel;
    private final long idMaterial;
    private static long idCounter = 1;

    public MaterialBB(String titulo, String idioma, boolean exclusivoVip) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.exclusivoVip = exclusivoVip;
        this.isDisponivel = true;
        this.idMaterial = idCounter++;
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

    public void setDisponivel(boolean disponivel) {
        isDisponivel = disponivel;
    }

    public void setExclusivoVip(boolean exclusivoVip) {
        this.exclusivoVip = exclusivoVip;
    }
}
