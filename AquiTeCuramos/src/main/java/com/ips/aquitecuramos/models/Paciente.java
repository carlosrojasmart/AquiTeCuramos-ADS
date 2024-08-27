package com.ips.aquitecuramos.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente {
    private int codigoEPS;
    private String id;
    private String nombre;
    private Date fechaNacimiento;
    private String sexo;
    private String nivelIngreso;
    private String correo;
    private List<Cita> citas;

    public Paciente() {
        citas = new ArrayList<>();
    }

    public int getCodigoEPS() {
        return codigoEPS;
    }

    public void setCodigoEPS(int codigoEPS) {
        this.codigoEPS = codigoEPS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNivelIngreso() {
        return nivelIngreso;
    }

    public void setNivelIngreso(String nivelIngreso) {
        this.nivelIngreso = nivelIngreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public void addCita(Cita cita) {
        this.citas.add(cita);
    }

}