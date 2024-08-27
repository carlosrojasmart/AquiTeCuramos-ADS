package com.ips.aquitecuramos.Stores;

import com.ips.aquitecuramos.models.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteStore {

    private static PacienteStore instance;
    private List<Paciente> pacientes;

    private PacienteStore() {
        pacientes = new ArrayList<>();
    }

    public static PacienteStore getInstance() {
        if (instance == null) {
            instance = new PacienteStore();
        }
        return instance;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public Paciente getPacientePorId(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

}
