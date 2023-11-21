package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.model.Administrador;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.Cliente;
import co.edu.uniquindio.agencia.model.Idiomas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
public class ControllerInicioSesion {

    @FXML
    private Button btnInicioSesion;

    @FXML
    private TextField labelContrasena;

    @FXML
    private TextField labelUsuario;

    @FXML
    private AnchorPane inicioSesion;

    @FXML
    private AnchorPane ventanaMenuAdmins;

    @FXML
    private AnchorPane ventanaMenu;

    @FXML
    private Text txtUsuario;

    @FXML
    private Text txtContra;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();



    public ControllerInicioSesion() throws AtributoVacioException, ElementoNoEncontradoException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }

    public void initialize() {

        ArrayList<Cliente> clientes = agenciaViajes.getClientes();
        ArrayList<Administrador> administradores = agenciaViajes.getAdministradores();
        String txtUs = Idiomas.getInstance().getResourceBundle().getString("txtUsuario");
        txtUsuario.setText(txtUs);

        String txtCon = Idiomas.getInstance().getResourceBundle().getString("txtContra");
        txtContra.setText(txtCon);

        String txtIS = Idiomas.getInstance().getResourceBundle().getString("txtIS");
        btnInicioSesion.setText(txtIS);
        System.out.println(agenciaViajes.sesionIniciada());

    }


    @FXML
    void mostrarMenu(ActionEvent event) throws IOException, AtributoVacioException, ElementoNoEncontradoException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
        String usuario = labelUsuario.getText();
        String contrasena = labelContrasena.getText();
        Cliente clienteSesion = agenciaViajes.sesionIniciada();
        boolean tipo = agenciaViajes.verificarCredenciales(usuario, contrasena);
        agenciaViajes.capturarSesion(usuario);
        String rutaVentana = "";

        System.out.println(clienteSesion.getNombre());





        if (tipo) {
            rutaVentana = "/ventanas/ventanaMenuAdmins.fxml";

        } else {
            rutaVentana = "/ventanas/ventanaMenu.fxml";

        }

        new ViewController(inicioSesion, rutaVentana);


    }










}





