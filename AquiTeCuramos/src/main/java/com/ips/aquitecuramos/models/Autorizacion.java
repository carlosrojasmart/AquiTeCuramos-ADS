package com.ips.aquitecuramos.models;

import java.util.Date;

public class Autorizacion {
    private boolean Permiso;
    private Date Fecha;

    public boolean isPermiso() {
        return Permiso;
    }

    public void setPermiso(boolean permiso) {
        Permiso = permiso;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public void validarPermiso() throws Exception {
        if (!Permiso) {
            throw new Exception("La autorización de EPS es necesaria para esta cita.");
        }
    }
}
