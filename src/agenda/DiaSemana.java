package src.agenda;
public enum DiaSemana {
    SEGUNDA("Segunda-Feira"),
    TERCA("Terça-Feira"),
    QUARTA("Quarta-Feira"),
    QUINTA("Quinta-Feira"),
    SEXTA("Sexta-Feira");
    
    private String Dia;
    DiaSemana(String Dia){
        this.Dia = Dia;
    }

    public String getString(){
        return this.Dia;
    } 
}
