package com.ips.aquitecuramos.Stores;

import com.ips.aquitecuramos.models.Especialidad;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EspecialidadStore {
    private static EspecialidadStore instance;
    private Map<String, Especialidad> especialidades;

    private EspecialidadStore() {
        especialidades = new HashMap<>();
    }

    public static EspecialidadStore getInstance() {
        if (instance == null) {
            instance = new EspecialidadStore();
        }
        return instance;
    }

    public void addEspecialidad(Especialidad especialidad) {
        especialidades.put(especialidad.getNombre(), especialidad);
    }

    public Especialidad getEspecialidad(String nombre) {
        return especialidades.get(nombre);
    }

    public Collection<Especialidad> getEspecialidades() {
        return especialidades.values();
    }
}
