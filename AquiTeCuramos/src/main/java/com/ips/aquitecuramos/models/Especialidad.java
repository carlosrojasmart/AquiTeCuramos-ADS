package com.ips.aquitecuramos.models;

import java.util.ArrayList;

public class Especialidad {
    private int idEspecializacion;
    private String nombre;
    private String pacientesHora;
    public ArrayList<String> especialistas;

    public int getIdEspecializacion() {
        return idEspecializacion;
    }

    public void setIdEspecializacion(int idEspecializacion) {
        this.idEspecializacion = idEspecializacion;
    }

    public String getPacientesHora() {
        return pacientesHora;
    }

    public void setPacientesHora(String pacientesHora) {
        this.pacientesHora = pacientesHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getEspecialistas() {
        return especialistas;
    }

    public void setEspecialistas(ArrayList<String> especialistas) {
        this.especialistas = especialistas;
    }
}