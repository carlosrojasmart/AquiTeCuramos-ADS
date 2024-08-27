package com.ips.aquitecuramos.Controllers;

import com.ips.aquitecuramos.models.Paciente;
import com.ips.aquitecuramos.Stores.PacienteStore;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.LocalDate;

public class RegistrarController {
    @FXML
    private ChoiceBox<String> tCodigoEPS;
    @FXML
    private TextField tId;
    @FXML
    private TextField tNombre;
    @FXML
    private DatePicker tFechaNacimiento;
    @FXML
    private ChoiceBox<String> tSexo;
    @FXML
    private ChoiceBox<String> tNivelIngreso;
    @FXML
    private TextField tCorreo;

    @FXML
    private void guardar() {
        String codigoEPS = tCodigoEPS.getValue();
        String id = tId.getText();
        String nombre = tNombre.getText();
        String fechaNacimiento = tFechaNacimiento.getValue().toString();
        String sexo = tSexo.getValue();
        String nivelIngreso = tNivelIngreso.getValue();
        String correo = tCorreo.getText();

        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setCodigoEPS(Integer.parseInt(codigoEPS));
        nuevoPaciente.setId(id);
        nuevoPaciente.setNombre(nombre);
        nuevoPaciente.setFechaNacimiento(Date.valueOf(LocalDate.parse(fechaNacimiento)));
        nuevoPaciente.setSexo(sexo);
        nuevoPaciente.setNivelIngreso(nivelIngreso);
        nuevoPaciente.setCorreo(correo);

        PacienteStore.getInstance().addPaciente(nuevoPaciente);

        limpiarCampos();
    }

    private void limpiarCampos() {
        tCodigoEPS.setValue(null);
        tId.clear();
        tNombre.clear();
        tFechaNacimiento.setValue(null);
        tSexo.setValue(null);
        tNivelIngreso.setValue(null);
        tCorreo.clear();
    }

}
