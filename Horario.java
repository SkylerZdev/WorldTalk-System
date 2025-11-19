public class Horario {
    private boolean disponivel;
    private HorarioPermitido horarioPermitido;


    //Classe suporte para o Funcionamento da Agenda.
    public Horario(HorarioPermitido horarioPermitido) {
        this.horarioPermitido = horarioPermitido;
        disponivel = true;
    }

    public HorarioPermitido getHorarioPermitido() {
        return horarioPermitido;
    }

    public int getHoraInicio() {
        return horarioPermitido.getHoraInicio();
    }
    public int getHoraFim() {
        return horarioPermitido.getHoraFim();
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}