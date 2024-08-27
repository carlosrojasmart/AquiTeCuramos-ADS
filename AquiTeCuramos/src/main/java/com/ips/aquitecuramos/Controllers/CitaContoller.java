package com.ips.aquitecuramos.Controllers;

import com.ips.aquitecuramos.Stores.PacienteStore;
import com.ips.aquitecuramos.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitaContoller {
    @FXML
    private TextField tPacienteRegistrado;
    @FXML
    private ChoiceBox<String> tCitaEps;
    @FXML
    private ChoiceBox<String> tAutorizacionEPS;
    @FXML
    private ChoiceBox<String> tEspecialidadRequerida;
    @FXML
    private ChoiceBox<String> tFechasDisponibles;

    @FXML
    public void initialize() {
        tEspecialidadRequerida.getItems().addAll("Cardiología", "Pediatría", "Medicina General");
        tCitaEps.getItems().addAll("Sí", "No");
        tAutorizacionEPS.getItems().addAll("Autorizado", "No Autorizado");

        tEspecialidadRequerida.setOnAction(e -> actualizarFechasDisponibles());
        tCitaEps.setOnAction(e -> manejarAutorizacion());
    }

    private void actualizarFechasDisponibles() {
        String especialidad = tEspecialidadRequerida.getValue();
        if (especialidad == null) {
            return;
        }

        List<String> horariosDisponibles = new ArrayList<>();
        LocalTime inicioManana = LocalTime.of(6, 0);
        LocalTime finManana = LocalTime.of(12, 0);
        LocalTime inicioTarde = LocalTime.of(12, 0);
        LocalTime finTarde = LocalTime.of(18, 0);
        int duracionMinutos;

        switch (especialidad) {
            case "Cardiología":
                duracionMinutos = 30;
                break;
            case "Pediatría":
                duracionMinutos = 20;
                break;
            case "Medicina General":
                duracionMinutos = 10;
                break;
            default:
                duracionMinutos = 0;
        }

        if (duracionMinutos > 0) {
            LocalTime tiempoActual = inicioManana;
            while (!tiempoActual.isAfter(finManana)) {
                horariosDisponibles.add(tiempoActual.toString());
                tiempoActual = tiempoActual.plusMinutes(duracionMinutos);
            }

            tiempoActual = inicioTarde;
            while (!tiempoActual.isAfter(finTarde)) {
                horariosDisponibles.add(tiempoActual.toString());
                tiempoActual = tiempoActual.plusMinutes(duracionMinutos);
            }
        }

        tFechasDisponibles.getItems().setAll(horariosDisponibles);
    }

    private void manejarAutorizacion() {
        String citaEps = tCitaEps.getValue();
        tAutorizacionEPS.setDisable(!"Sí".equals(citaEps));
        if (!"Sí".equals(citaEps)) {
            tAutorizacionEPS.setValue(null); // Limpiar selección cuando está deshabilitado
        }
    }

    @FXML
    private void guardar() {
        String idPaciente = tPacienteRegistrado.getText();
        Paciente paciente = PacienteStore.getInstance().getPacientePorId(idPaciente);

        if (paciente == null) {
            mostrarAlerta("Error", "Paciente no registrado", "El ID del paciente no está registrado.");
            return;
        }

        boolean citaEps = "Sí".equals(tCitaEps.getValue());
        String autorizacionEPS = tAutorizacionEPS.getValue();
        String especialidad = tEspecialidadRequerida.getValue();
        String horaCitaStr = tFechasDisponibles.getValue();
        LocalDate fechaActual = LocalDate.now();

        if (especialidad == null || horaCitaStr == null) {
            mostrarAlerta("Error", "Datos incompletos", "Debe seleccionar la especialidad y la fecha disponible.");
            return;
        }

        Date fechaCitaSql = Date.valueOf(fechaActual);

        for (Cita cita : paciente.getCitas()) {
            if (cita.getFechaCita().equals(fechaCitaSql) && cita.getHoraCita().equals(horaCitaStr)) {
                mostrarAlerta("Error", "Cita duplicada", "Ya existe una cita en la fecha y hora seleccionada.");
                return;
            }
        }

        Cita nuevaCita = new Cita();
        nuevaCita.setCitaEps(citaEps);
        nuevaCita.setAutorizacionEPS(autorizacionEPS);
        nuevaCita.setFechaCita(fechaCitaSql);
        nuevaCita.setEspecialidad(especialidad);
        nuevaCita.setHoraCita(horaCitaStr);  // Asignar la hora de la cita

        paciente.addCita(nuevaCita);

        limpiarCampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Correo Enviado");
        alert.setHeaderText("Correo enviado a:");
        alert.setContentText("El correo ha sido enviado a: " + paciente.getCorreo());
        alert.showAndWait();
    }

    private void limpiarCampos() {
        tPacienteRegistrado.clear();
        tCitaEps.setValue(null);
        tAutorizacionEPS.setValue(null);
        tEspecialidadRequerida.setValue(null);
        tFechasDisponibles.setValue(null);
    }

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}


