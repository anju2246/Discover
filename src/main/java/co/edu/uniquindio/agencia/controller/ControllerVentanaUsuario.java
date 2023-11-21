package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import co.edu.uniquindio.agencia.exceptions.DestinoRepetidoException;
import co.edu.uniquindio.agencia.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.agencia.exceptions.RutaInvalidaException;
import co.edu.uniquindio.agencia.model.Administrador;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.Cliente;
import co.edu.uniquindio.agencia.model.Idiomas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ControllerVentanaUsuario {

    @FXML
    private Label lblDestinos;

    @FXML
    private Label lblDestinos1;

    @FXML
    private Label lblDestinos11;

    @FXML
    private Label lblDestinos2;

    @FXML
    private Label lblMisViajes1;

    @FXML
    private Label lblMisViajes2;

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnVolver;

    @FXML
    private AnchorPane ventanaUsuario;

    @FXML
    private AnchorPane ventanaInicio;

    private Cliente clienteSesion;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();

    public ControllerVentanaUsuario() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }

    public void initialize() {

        ArrayList<Cliente> clientes = agenciaViajes.getClientes();
        agenciaViajes.sesionIniciada();

        String btn1 = Idiomas.getInstance().getResourceBundle().getString("lblDestinos2");
        lblDestinos2.setText(btn1);

        String btn2 = Idiomas.getInstance().getResourceBundle().getString("btnEditar");
        btnEditar.setText(btn2);

        String btn3 = Idiomas.getInstance().getResourceBundle().getString("btnVolver");
        btnVolver.setText(btn3);

        cargarInfoUsuario();
    }


    @FXML
    void volverEvent(ActionEvent event) throws IOException {
        new ViewController(ventanaUsuario, "/ventanas/VentanaMenu.fxml");

    }

    public void cargarInfoUsuario() {
        Cliente clienteSesion = agenciaViajes.sesionIniciada();
        if (clienteSesion != null) {
            // El cliente está inicializado, ahora puedes acceder a sus propiedades
            lblDestinos1.setText(clienteSesion.getCorreo());
            lblDestinos11.setText(clienteSesion.getTelefono());
            lblDestinos.setText(clienteSesion.getNombre());
        } else {
            // Manejar el caso en que el cliente no está inicializado
            System.out.println("Cliente no inicializado");
        }
    }

    public void editarInfoUsuario () {
        lblDestinos1.setText(agenciaViajes.sesionIniciada().getCorreo());
        lblDestinos11.setText(agenciaViajes.sesionIniciada().getTelefono());
        lblDestinos.setText(agenciaViajes.sesionIniciada().getNombre());
    }






}
