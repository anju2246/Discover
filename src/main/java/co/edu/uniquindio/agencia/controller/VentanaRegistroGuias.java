package co.edu.uniquindio.agencia.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agencia.app.App;
import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.GuiaTuristico;
import co.edu.uniquindio.agencia.model.Idiomas;
import co.edu.uniquindio.agencia.model.IdiomasGuia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;

public class VentanaRegistroGuias {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegistrarGuia;



    @FXML
    private CheckBox ckEspanol;

    @FXML
    private CheckBox ckFrances;

    @FXML
    private CheckBox ckIngles;

    @FXML
    private Label exp;

    @FXML
    private Label id;

    @FXML
    private Label idiomas;

    @FXML
    private Label nombre;

    @FXML
    private TextField txtExperiencia;

    @FXML
    private Label txtGuiasRegistrados;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label txtRegistroGuias;
    @FXML
    private TableColumn<GuiaTuristico, String> columNombre;

    @FXML
    private TableColumn<GuiaTuristico, String> columnExperiencia;

    @FXML
    private TableColumn<GuiaTuristico, String> columnIdentificacion;

    @FXML
    private TableColumn<GuiaTuristico, IdiomasGuia> columnIdiomas;

    @FXML
    private TableView<GuiaTuristico> tabGuiasRegistrados;



    @FXML
    private AnchorPane ventanaRegistroGuias;

    private App main;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();



    ObservableList<GuiaTuristico> listaGuias = FXCollections.observableArrayList(AgenciaViajes.getInstance().getGuias());


    public VentanaRegistroGuias() throws RutaInvalidaException, AtributoVacioException, InformacionRepetidaException, DestinoRepetidoException {
    }


    @FXML
    void actualizarEvent(ActionEvent event) throws RutaInvalidaException {
        actualizarAction();

    }

    private void actualizarAction() throws RutaInvalidaException {

    }

    @FXML
    void eliminarEvent(ActionEvent event) throws AtributoVacioException, RutaInvalidaException, DestinoRepetidoException, ElementoNoEncontradoException, InformacionRepetidaException {
        eliminarAction();
    }

    @SneakyThrows
    @FXML
    void registrarGuiaEvent(ActionEvent event) {
        registrarGuiaAction();

    }

    private void registrarGuiaAction() throws ElementoNoEncontradoException {

        try {
            String nombre = txtNombre.getText();
            String identificacion = txtIdentificacion.getText();
            String experiencia = txtExperiencia.getText();

            if (!ckEspanol.isSelected() && !ckIngles.isSelected() && !ckFrances.isSelected()) {

                throw new ElementoNoEncontradoException("Debes seleccionar al menos un idioma.");
            }

            // Recopilar los idiomas seleccionados
            List<IdiomasGuia> idiomasSeleccionados = new ArrayList<>();

            if (ckEspanol.isSelected()) {
                idiomasSeleccionados.add(IdiomasGuia.ESPANOL);
            }
            if (ckIngles.isSelected()) {
                idiomasSeleccionados.add(IdiomasGuia.INGLES);
            }
            if (ckFrances.isSelected()) {
                idiomasSeleccionados.add(IdiomasGuia.FRANCES);
            }

            // Llamar al método de registro en la clase principal
            GuiaTuristico guia = agenciaViajes.registrarGuias(nombre, identificacion, idiomasSeleccionados, experiencia);

          //  this.agenciaViajes.getGuias().add(guia);
            this.tabGuiasRegistrados.setItems(listaGuias);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el guia con la cedula  " + txtIdentificacion.getText());
            alert.show();

            // Limpia los campos después del registro
            txtNombre.clear();
            txtIdentificacion.clear();
            txtExperiencia.clear();
            ckEspanol.setSelected(false);
            ckIngles.setSelected(false);
            ckFrances.setSelected(false);

            // Actualizar la tabla de guías registrados u otra lógica necesaria
            actualizarTablaGuias();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

    public void actualizarTablaGuias() throws RutaInvalidaException {
        listaGuias = FXCollections.observableArrayList(agenciaViajes.getGuias());
        tabGuiasRegistrados.getItems().clear();
        tabGuiasRegistrados.setItems(listaGuias);
        tablaguias();
        tabGuiasRegistrados.refresh();
    }

    private void eliminarAction() throws AtributoVacioException, RutaInvalidaException {
        GuiaTuristico guiaSeleccionado = tabGuiasRegistrados.getSelectionModel().getSelectedItem();

        if (guiaSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                AgenciaViajes.getInstance().eliminarGuia(guiaSeleccionado.getIdentificacion());

                // Actualiza la tabla de guías
                actualizarTablaGuias();
            } catch (ElementoNoEncontradoException | InformacionRepetidaException | DestinoRepetidoException e) {
                // Manejar la excepción si el guía no se encuentra
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            }
        } else {
            // Si no se selecciona ningún guía, muestra un mensaje de error
            throw new AtributoVacioException("Selecciona un guía para eliminar.");
        }
    }



    @FXML
    void initialize() throws RutaInvalidaException {
        tablaguias();
        actualizarTablaGuias();

        String txtUs = Idiomas.getInstance().getResourceBundle().getString("txtRegistroGuias");
        txtRegistroGuias.setText(txtUs);

        String txtUs1 = Idiomas.getInstance().getResourceBundle().getString("nombre");
        nombre.setText(txtUs1);

        String txtUs2 = Idiomas.getInstance().getResourceBundle().getString("id");
        id.setText(txtUs2);

        String txtUs3 = Idiomas.getInstance().getResourceBundle().getString("exp");
        exp.setText(txtUs3);

        String txtUs4 = Idiomas.getInstance().getResourceBundle().getString("idiomas");
        idiomas.setText(txtUs4);

        String txtUs5 = Idiomas.getInstance().getResourceBundle().getString("idiomas");
        idiomas.setText(txtUs5);

        String txtUs6 = Idiomas.getInstance().getResourceBundle().getString("btnRegistrarGuia");
        btnRegistrarGuia.setText(txtUs6);

        String txtUs7 = Idiomas.getInstance().getResourceBundle().getString("btnActualizar");
        btnActualizar.setText(txtUs7);

        String txtUs8 = Idiomas.getInstance().getResourceBundle().getString("btnEliminar");
        btnEliminar.setText(txtUs8);


    }

    void tablaguias() throws  RutaInvalidaException {

        tabGuiasRegistrados.setItems(listaGuias);

        ckEspanol.setSelected(false);
        ckIngles.setSelected(false);
        ckFrances.setSelected(false);

        columNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        columnExperiencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExp()));




        tabGuiasRegistrados.refresh();

    }

    @FXML
    void volverEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaRegistroGuias, "/ventanas/VentanaMenuAdmins.fxml");

    }


}