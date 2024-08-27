package com.ips.aquitecuramos.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Cita {
    private boolean citaEps;
    private String autorizacionEPS;
    private Date fechaCita;
    private String especialidad;
    private String horaCita;

    public boolean isCitaEps() {
        return citaEps;
    }

    public void setCitaEps(boolean citaEps) {
        this.citaEps = citaEps;
    }

    public String getAutorizacionEPS() {
        return autorizacionEPS;
    }

    public void setAutorizacionEPS(String autorizacionEPS) {
        this.autorizacionEPS = autorizacionEPS;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    @Override
    public String toString() {
        return "Cita de " + especialidad + " con fecha " + fechaCita + " y hora: " + horaCita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(fechaCita, cita.fechaCita) &&
                Objects.equals(horaCita, cita.horaCita) &&
                Objects.equals(especialidad, cita.especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaCita, horaCita, especialidad);
    }
}