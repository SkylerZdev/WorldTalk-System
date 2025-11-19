public enum HorarioPermitido {
    H8_10(8,10),
    H10_12(10,12),
    H14_16(14,16),
    H16_18(16,18),
    H18_20(18,20);
    
    private final int horaInicio;
    private final int horaFim;

    //Definição da Grade de Horarios Permitidos

    public int getHoraInicio() {
        return horaInicio;
    }
    public int getHoraFim() {
        return horaFim;
    }
    HorarioPermitido(int horaInicio, int horaFim) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }
    
}
