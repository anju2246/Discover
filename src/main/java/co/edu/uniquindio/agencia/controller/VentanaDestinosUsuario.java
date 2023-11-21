package co.edu.uniquindio.agencia.controller;
import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import co.edu.uniquindio.agencia.exceptions.DestinoRepetidoException;
import co.edu.uniquindio.agencia.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.agencia.exceptions.RutaInvalidaException;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.PaquetesTuristicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class VentanaDestinosUsuario {

    @FXML
    private TableView<PaquetesTuristicos> tablaPaquetes;

    @FXML
    private TableColumn<PaquetesTuristicos, String> colNombre;

    @FXML
    private TableColumn<PaquetesTuristicos, String> colDescripcion;

    @FXML
    private TableColumn<PaquetesTuristicos, Double> colPrecio;

    @FXML
    private TableColumn<PaquetesTuristicos, LocalDate> colFechaInicio;

    @FXML
    private TableColumn<PaquetesTuristicos, LocalDate> colFechaFin;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();

    private ObservableList<PaquetesTuristicos> listaPaquetes = FXCollections.observableArrayList();

    public VentanaDestinosUsuario() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }

    @FXML
    void initialize() {
        inicializarTablaPaquetes();
        cargarDatosPaquetes();
    }

    private void inicializarTablaPaquetes() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaDisponibleInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaDisponibleFin"));

        tablaPaquetes.setItems(listaPaquetes);
    }

    private void cargarDatosPaquetes() {
        // Obtener la lista de paquetes turísticos desde AgenciaViajes
        listaPaquetes.addAll(agenciaViajes.getPaquetes());
    }


}
