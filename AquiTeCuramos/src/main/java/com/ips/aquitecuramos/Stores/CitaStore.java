package com.ips.aquitecuramos.Stores;

import com.ips.aquitecuramos.models.Cita;

import java.util.*;

public class CitaStore {

    private static CitaStore instance;

    private Map<String, Cita> citas;

    private CitaStore() {
        citas = new HashMap<>();
    }

    public static CitaStore getInstance() {
        if (instance == null) {
            instance = new CitaStore();
        }
        return instance;
    }


    public List<Cita> getCitas() {
        return new ArrayList<>(citas.values());
    }
}
