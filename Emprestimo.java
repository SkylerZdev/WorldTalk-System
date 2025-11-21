public class Emprestimo {
    private MaterialBB material;
    private Aluno aluno;
    private boolean devolvido;
    private final long id;
    private static long idCounter = 1;

    public Emprestimo() {
        this.id = idCounter++;
    }

    public Emprestimo(MaterialBB material, Aluno aluno) {
        this.material = material;
        this.aluno = aluno;
        this.devolvido = false;
        this.id = idCounter++;
        this.material.setDisponivel(false);
    }

    public MaterialBB getMaterial() {
        return material;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public long getId() {
        return id;
    }

    public void setMaterial(MaterialBB material) {
        this.material = material;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void marcarComoDevolvido() {
        this.devolvido = true;
        this.material.setDisponivel(true);
    }
}
