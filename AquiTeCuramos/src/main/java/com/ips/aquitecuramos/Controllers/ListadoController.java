package com.ips.aquitecuramos.Controllers;

import com.ips.aquitecuramos.models.Cita;
import com.ips.aquitecuramos.models.Paciente;
import com.ips.aquitecuramos.Stores.PacienteStore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ListadoController implements Initializable {
    @FXML
    public TableView<Paciente> tabla;
    @FXML
    public TableColumn<Paciente, Integer> codigoEPS;
    @FXML
    public TableColumn<Paciente, String> Id;
    @FXML
    public TableColumn<Paciente, String> nombre;
    @FXML
    public TableColumn<Paciente, Date> fechaNacimiento;
    @FXML
    public TableColumn<Paciente, String> sexo;
    @FXML
    public TableColumn<Paciente, String> nivelIngreso;
    @FXML
    public TableColumn<Paciente, String> correo;
    @FXML
    public TableColumn<Paciente, String> citas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PacienteStore ps = PacienteStore.getInstance();
        codigoEPS.setCellValueFactory(new PropertyValueFactory<>("codigoEPS"));
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        nivelIngreso.setCellValueFactory(new PropertyValueFactory<>("nivelIngreso"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        citas.setCellValueFactory(cellData -> {
            Paciente paciente = cellData.getValue();
            StringBuilder citasStr = new StringBuilder();
            if (paciente.getCitas() != null) {
                for (Cita cita : paciente.getCitas()) {
                    citasStr.append(cita.toString()).append("\n");
                }
            } else {
                citasStr.append("Sin citas");
            }
            return new SimpleStringProperty(citasStr.toString());
        });

        ObservableList<Paciente> pacientes = FXCollections.observableArrayList(ps.getPacientes());
        tabla.setItems(pacientes);
    }
}

