package co.edu.uniquindio.agencia.controller;

import java.io.IOException;
import java.util.Objects;

import co.edu.uniquindio.agencia.app.App;
import co.edu.uniquindio.agencia.exceptions.AtributoVacioException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewController {

    private AnchorPane currentAnchorPane;

    public ViewController(AnchorPane currentAnchorPane, String fxmlPath) {
        this.currentAnchorPane = currentAnchorPane;
        cargarVentana(fxmlPath);
    }

    private void cargarVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Configuración de la nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) currentAnchorPane.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en tu aplicación
        }
    }


}

