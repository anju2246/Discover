package co.edu.uniquindio.agencia.controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.GuiaTuristico;
import co.edu.uniquindio.agencia.model.Idiomas;
import co.edu.uniquindio.agencia.model.PaquetesTuristicos;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;

public class VentanaPaquetesUsuario {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegistrarPaquete;

    @FXML
    private TableColumn<PaquetesTuristicos, String> columNombrePaquete;

    @FXML
    private TableColumn<PaquetesTuristicos, String> columnDescripcion;

    @FXML
    private TableColumn<PaquetesTuristicos, Double> columnPrecio;

    @FXML
    private TableColumn<PaquetesTuristicos, String> columnServiciosAdicionales;

    @FXML
    private TableColumn<PaquetesTuristicos, Integer> columnCupoMax;

    @FXML
    private TableColumn<PaquetesTuristicos, LocalDate> columnFechaInicio;

    @FXML
    private TableColumn<PaquetesTuristicos, LocalDate> columnFechaFin;

    @FXML
    private TableColumn<PaquetesTuristicos, GuiaTuristico> columnGuia;

    @FXML
    private TableView<PaquetesTuristicos> tabPaquetesRegistrados;

    @FXML
    private TextField txtNombrePaquete;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtServiciosAdicionales;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCupoMax;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private ComboBox<GuiaTuristico> comboGuias;

    @FXML
    private AnchorPane ventanaPaquetesUsuario;

    @FXML
    private Button btnReservar;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();

    private ObservableList<PaquetesTuristicos> listaPaquetes = FXCollections.observableArrayList();

    public VentanaPaquetesUsuario() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }

    @FXML
    void actualizarEvent(ActionEvent event) throws RutaInvalidaException {
        actualizarAction();
    }

    private void actualizarAction() throws RutaInvalidaException {
        // Implementa la lógica de actualización de paquetes turísticos
    }

    @FXML
    void eliminarEvent(ActionEvent event) throws AtributoVacioException, RutaInvalidaException {
        eliminarAction();
    }

    private void eliminarAction() throws AtributoVacioException, RutaInvalidaException {
        PaquetesTuristicos paqueteSeleccionado = tabPaquetesRegistrados.getSelectionModel().getSelectedItem();

        if (paqueteSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                agenciaViajes.eliminarPaquete(paqueteSeleccionado.getNombre());

                // Actualiza la tabla de paquetes turísticos
                actualizarTablaPaquetes();
            } catch (ElementoNoEncontradoException e) {
                // Manejar la excepción si el paquete no se encuentra
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            }
        } else {
            // Si no se selecciona ningún paquete, muestra un mensaje de error
            throw new AtributoVacioException("Selecciona un paquete para eliminar.");
        }
    }



    public void actualizarTablaPaquetes() throws RutaInvalidaException {
        listaPaquetes = FXCollections.observableArrayList(agenciaViajes.getPaquetes());
        tabPaquetesRegistrados.getItems().clear();
        tabPaquetesRegistrados.setItems(listaPaquetes);
        tablaPaquetes();
        tabPaquetesRegistrados.refresh();
    }

    @FXML
    void initialize() throws RutaInvalidaException {
        tablaPaquetes();
        actualizarTablaPaquetes();
        String btn1 = Idiomas.getInstance().getResourceBundle().getString("btnReservar");
        btnReservar.setText(btn1);

    }

    @FXML
    void volverEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaPaquetesUsuario, "/ventanas/VentanaMenu.fxml");

    }

    void tablaPaquetes() throws RutaInvalidaException {
        tabPaquetesRegistrados.setItems(listaPaquetes);

        columNombrePaquete.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnServiciosAdicionales.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServiciosAdicionales()));
        columnPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        columnCupoMax.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCupoMax()));
        columnFechaInicio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaDisponibleInicio()));
        columnFechaFin.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaDisponibleFin()));
        columnGuia.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGuia()));

        tabPaquetesRegistrados.refresh();
    }



    private void limpiarCamposRegistroPaquete() {
        txtNombrePaquete.clear();
        txtDescripcion.clear();
        txtServiciosAdicionales.clear();
        txtPrecio.clear();
        txtCupoMax.clear();
        dateFechaInicio.setValue(null);
        dateFechaFin.setValue(null);
    }

    @FXML
    void reservarPaqueteEvent(ActionEvent event) {
        reservarPaqueteAction();
    }

    private void reservarPaqueteAction() {
        try {
            PaquetesTuristicos paqueteSeleccionado = tabPaquetesRegistrados.getSelectionModel().getSelectedItem();

            if (paqueteSeleccionado != null) {
                // Lógica para realizar la reserva del paquete
                // Puedes imprimir en la consola o mostrar un mensaje de éxito en la interfaz gráfica
                System.out.println("Paquete reservado con éxito: " + paqueteSeleccionado.getNombre());
            } else {
                // Si no se selecciona ningún paquete, muestra un mensaje de error
                throw new AtributoVacioException("Selecciona un paquete para reservar.");
            }
        } catch (Exception e) {
            // Manejar la excepción si ocurre algún error durante la reserva
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }


}