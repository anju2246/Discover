package co.edu.uniquindio.agencia.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class InicioController {

    public void mostrarMensaje(ActionEvent e){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje");
        alert.setContentText("Esta es una prueba");
        alert.show();

    }

}
