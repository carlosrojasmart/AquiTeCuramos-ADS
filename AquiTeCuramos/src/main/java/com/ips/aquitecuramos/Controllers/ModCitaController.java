package com.ips.aquitecuramos.Controllers;

import com.ips.aquitecuramos.Stores.PacienteStore;
import com.ips.aquitecuramos.models.Cita;
import com.ips.aquitecuramos.models.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModCitaController {
    @FXML
    private TextField tPacienteRegistrado;
    @FXML
    private ChoiceBox<Cita> tCitasExistentes;
    @FXML
    private ChoiceBox<String> tEspecialidadRequerida;
    @FXML
    private ChoiceBox<String> tFechasDisponibles;

    @FXML
    public void initialize() {
        tEspecialidadRequerida.getItems().addAll("Cardiología", "Pediatría", "Medicina General");
        tEspecialidadRequerida.setOnAction(e -> actualizarFechasDisponibles());
        tCitasExistentes.setOnAction(e -> cargarDetallesCita());
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

    private void cargarDetallesCita() {
        Cita citaSeleccionada = tCitasExistentes.getValue();
        if (citaSeleccionada != null) {
            tEspecialidadRequerida.setValue(citaSeleccionada.getEspecialidad());
            tFechasDisponibles.setValue(citaSeleccionada.getHoraCita());
        }
    }

    @FXML
    private void cargarCitas() {
        String idPaciente = tPacienteRegistrado.getText();
        Paciente paciente = PacienteStore.getInstance().getPacientePorId(idPaciente);

        if (paciente == null) {
            mostrarAlerta("Error", "Paciente no registrado", "El ID del paciente no está registrado.");
            return;
        }

        tCitasExistentes.getItems().setAll(paciente.getCitas());
    }

    @FXML
    private void guardarCambios() {
        Cita citaSeleccionada = tCitasExistentes.getValue();
        if (citaSeleccionada == null) {
            mostrarAlerta("Error", "Cita no seleccionada", "Debe seleccionar una cita para modificar.");
            return;
        }

        String especialidad = tEspecialidadRequerida.getValue();
        String horaCitaStr = tFechasDisponibles.getValue();
        LocalDate fechaActual = LocalDate.now();

        if (especialidad == null || horaCitaStr == null) {
            mostrarAlerta("Error", "Datos incompletos", "Debe seleccionar la especialidad y la fecha disponible.");
            return;
        }

        Date fechaCitaSql = Date.valueOf(fechaActual);

        for (Cita cita : tCitasExistentes.getItems()) {
            if (!cita.equals(citaSeleccionada) &&
                    cita.getFechaCita().equals(fechaCitaSql) &&
                    cita.getHoraCita().equals(horaCitaStr)) {
                mostrarAlerta("Error", "Cita duplicada", "Ya existe una cita en la fecha y hora seleccionada.");
                return;
            }
        }

        citaSeleccionada.setEspecialidad(especialidad);
        citaSeleccionada.setHoraCita(horaCitaStr);
        citaSeleccionada.setFechaCita(fechaCitaSql);

        mostrarAlerta("Éxito", "Cita modificada", "La cita ha sido modificada exitosamente.");
    }

    @FXML
    private void eliminarCita() {
        Cita citaSeleccionada = tCitasExistentes.getValue();
        if (citaSeleccionada == null) {
            mostrarAlerta("Error", "Cita no seleccionada", "Debe seleccionar una cita para eliminar.");
            return;
        }

        String idPaciente = tPacienteRegistrado.getText();
        Paciente paciente = PacienteStore.getInstance().getPacientePorId(idPaciente);

        if (paciente == null) {
            mostrarAlerta("Error", "Paciente no registrado", "El ID del paciente no está registrado.");
            return;
        }

        paciente.getCitas().remove(citaSeleccionada);
        tCitasExistentes.getItems().remove(citaSeleccionada);

        mostrarAlerta("Éxito", "Cita eliminada", "La cita ha sido eliminada exitosamente.");
    }

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
