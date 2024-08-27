package com.ips.aquitecuramos.models;

public class Especialista {
    private String IdEspecialista;
    private String nombreApellido;
    private String especialidad;
    private String turno;

    static {
        Especialista especialista = new Especialista();
        especialista.setIdEspecialista("12345");
        especialista.setNombreApellido("Juan Rincon");
        especialista.setEspecialidad("Cardiología");
        especialista.setTurno("6am a 12am");
    }

    public String getIdEspecialista() {
        return IdEspecialista;
    }

    public void setIdEspecialista(String idEspecialista) {
        IdEspecialista = idEspecialista;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }


}
