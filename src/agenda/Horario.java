package src.agenda;

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

    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getHoraInicio(), this.getHoraFim());
    }

    @Override
    public boolean equals(Object o){
        if  (o == this)  {  return true;    }
        if (!(o instanceof Horario) || (o == null)){   return false;    }
        Horario outro = (Horario)o;
        return this.getHoraInicio() == outro.getHoraInicio() && this.getHoraFim() == outro.getHoraFim();
    }

    @Override
    public String toString(){
        return getHoraInicio() + ":00 - " + getHoraFim() + ":00 // Disponibilidade: " + (this.disponivel? "Livre" : "Ocupado");
    }
}