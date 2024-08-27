package com.ips.aquitecuramos.Controllers;

import com.ips.aquitecuramos.models.Paciente;
import com.ips.aquitecuramos.Stores.PacienteStore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InicialController {
    @FXML
    public MenuItem optSalir;
    @FXML
    public BorderPane contenedor;

    public InicialController() throws IOException {
    }

    @FXML
    public void salir() {
        System.exit(0);
    }

    @FXML
    public void registrarPaciente() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ips/aquitecuramos/registrar.fxml"));
        contenedor.setCenter(pane);
    }

    @FXML
    public void listarPaciente() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ips/aquitecuramos/listado.fxml"));
        contenedor.setCenter(pane);
    }

    public void citarPaciente() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ips/aquitecuramos/cita.fxml"));
        contenedor.setCenter(pane);
    }

    public void modCitaPaciente() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ips/aquitecuramos/ModCitas.fxml"));
        contenedor.setCenter(pane);
    }


    @FXML
    public void cargarArchivo() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(contenedor.getScene().getWindow());

        if (file != null) {
            List<Paciente> pacientes = leerPacientesDesdeArchivo(file);
            PacienteStore store = PacienteStore.getInstance();
            for (Paciente p : pacientes) {
                store.addPaciente(p);
            }
        }
    }

    private List<Paciente> leerPacientesDesdeArchivo(File file) throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 7) {
                    Paciente p = new Paciente();
                    p.setCodigoEPS(Integer.parseInt(fields[0]));
                    p.setId(fields[1]);
                    p.setNombre(fields[2]);
                    LocalDate fechaNacimiento = LocalDate.parse(fields[3], formatter);
                    p.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
                    p.setSexo(fields[4]);
                    p.setNivelIngreso(fields[5]);
                    p.setCorreo(fields[6]);
                    pacientes.add(p);
                }
            }
        }
        return pacientes;
    }

}
