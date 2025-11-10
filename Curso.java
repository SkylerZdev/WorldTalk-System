public class Curso {
    private String infoBase;
    private double mensalidade;
    private int horarios;
    private int niveis;

    // Getters
    public String getInfoBase() {
        return infoBase;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public int getHorarios() {
        return horarios;
    }
    public int getNiveis() {
        return niveis;
    }

    // Setters
    public void setInfoBase(String infoBase) {
        this.infoBase = infoBase;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void setHorarios(int horarios) {
        this.horarios = horarios;
    }
    public void setNiveis(int niveis) {
        this.niveis = niveis;
    }
}