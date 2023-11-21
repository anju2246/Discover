
package co.edu.uniquindio.agencia.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import co.edu.uniquindio.agencia.exceptions.DestinoRepetidoException;
import co.edu.uniquindio.agencia.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.agencia.exceptions.RutaInvalidaException;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.Idiomas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ControllerMenuAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDestinos;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnGestion;

    @FXML
    private Button btnGuias;

    @FXML
    private Button btnPaquetes;

    @FXML
    private Button btnVentanaAdmin;

    @FXML
    private Label lblDestinos;

    @FXML
    private AnchorPane ventanaMenuAdmins;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();

    public ControllerMenuAdmin() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }


    @FXML
    void destinosEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaMenuAdmins, "/ventanas/ventanaRegistroDestinos.fxml");

    }

    @FXML
    void volverEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaMenuAdmins, "/ventanas/inicio.fxml");

    }



    @FXML
    void guiasEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaMenuAdmins, "/ventanas/ventanaRegistroGuias.fxml");

    }



    @FXML
    void paquetesEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaMenuAdmins, "/ventanas/ventanaRegistroPaquetes.fxml");

    }

    @FXML
    void initialize() {

        String btn1 = Idiomas.getInstance().getResourceBundle().getString("btnDestinos");
        btnDestinos.setText(btn1);

        String btn2 = Idiomas.getInstance().getResourceBundle().getString("btnGuias");
        btnGuias.setText(btn2);

        agenciaViajes.sesionIniciada();
    }



}
