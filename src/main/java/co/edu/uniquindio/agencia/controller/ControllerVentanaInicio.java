package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import co.edu.uniquindio.agencia.exceptions.DestinoRepetidoException;
import co.edu.uniquindio.agencia.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.agencia.exceptions.RutaInvalidaException;
import co.edu.uniquindio.agencia.model.AgenciaViajes;
import co.edu.uniquindio.agencia.model.Destino;
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

public class ControllerVentanaInicio {

    @FXML
    private Button btnBuscarDestinos;

    @FXML
    private Button btnBuscarPaquetes;

    @FXML
    private Button btnUsuario;

    @FXML
    private AnchorPane ventanaPaquetes;

    @FXML
    private Label lblDestinos;

    @FXML
    private AnchorPane ventanaMenuInicio;

    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private ImageView imagen3;

    @FXML
    private ImageView imagen4;

    private final AgenciaViajes agenciaViajes = AgenciaViajes.getInstance();


    private Button botonSeleccionado;

    public ControllerVentanaInicio() throws AtributoVacioException, DestinoRepetidoException, RutaInvalidaException, InformacionRepetidaException {
    }

    public void initialize() {
        botonSeleccionado = btnBuscarDestinos;
        ArrayList<Destino> destinos = agenciaViajes.getDestinos();
        String btn1 = Idiomas.getInstance().getResourceBundle().getString("btnBuscarPaquetes");
        btnBuscarPaquetes.setText(btn1);
        String btn2 = Idiomas.getInstance().getResourceBundle().getString("btnBuscarDestinos");
        btnBuscarDestinos.setText(btn2);
        String btn3 = Idiomas.getInstance().getResourceBundle().getString("btnUsuario");
        btnUsuario.setText(btn3);

        }

    @FXML
    void mostrarBuscarPaquetes(ActionEvent event) throws IOException {
        new ViewController(ventanaMenuInicio, "/ventanas/ventanaPaquetesUsuario.fxml");

    }
    @FXML
    void mostrarUsuario() throws IOException {
        new ViewController(ventanaMenuInicio, "/ventanas/ventanaUsuario.fxml");

    }
    @FXML
    void onBotonClick(MouseEvent event) {
        Button nuevoBoton = (Button) event.getSource();

        if (botonSeleccionado != nuevoBoton) {
            botonSeleccionado.getStyleClass().remove("botonSeleccionado");
            nuevoBoton.getStyleClass().add("botonSeleccionado");
            botonSeleccionado = nuevoBoton;
        }else{
            nuevoBoton.getStyleClass().add("botonSeleccionado");
        }
    }


}
