package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.model.Administrador;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.Cliente;
import co.edu.uniquindio.agencia.model.Idiomas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerInicio  {

    @FXML
    private Button iniciarSesionBtn;

    @FXML
    private AnchorPane ventanaInicio;

    @FXML
    private AnchorPane inicio;

    @FXML
    private Button crearUsuarioBtn;

    @FXML
    private Button BtnCambiarIdioma;
    @FXML
    private Text txtUsuario;


    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();

    public ControllerInicio() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }


    public void initialize() {

        ArrayList<Cliente> clientes = agenciaViajes.getClientes();
        ArrayList<Administrador> administradores = agenciaViajes.getAdministradores();
        agenciaViajes.imprimirClientes();
        cargarTextos();



    }

    @FXML
    void mostrarInicioSesion(ActionEvent event) throws IOException {
        new ViewController(ventanaInicio, "/ventanas/inicioSesion.fxml");

    }
    @FXML
    void mostrarCrearCliente(ActionEvent event) throws IOException {
        new ViewController(ventanaInicio, "/ventanas/ventanaRegistroCliente.fxml");

    }

    @FXML
    public void cambiarIdioma(){
        if( Idiomas.getInstance().getIdiomaActual().equals("es") ){
            Idiomas.getInstance().cambiarIdioma("en");
        }else{
            Idiomas.getInstance().cambiarIdioma("es");

        }
        cargarTextos();


    }

    public void cargarTextos(){
        String txtBtn1 = Idiomas.getInstance().getResourceBundle().getString("BtnCambiarIdioma");
        BtnCambiarIdioma.setText(txtBtn1);



        String txtBtn2 = Idiomas.getInstance().getResourceBundle().getString("BtnIS");
        iniciarSesionBtn.setText(txtBtn2);

        String txtBtn3 = Idiomas.getInstance().getResourceBundle().getString("BtnUser");
        crearUsuarioBtn.setText(txtBtn3);

    }






}
