package com.jer.liga_futbol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class menuController {
    // Instancia de la clase HelloApplication
    private HelloApplication helloApplication = new HelloApplication();
    private busquedaController busquedaController = new busquedaController();
    private ficheroFutbol fichero = new ficheroFutbol();

    @FXML
    public void introducir() throws IOException {
        // Cambio la scene
        helloApplication.setRoot("crear-view");

    }

    @FXML
    public void ordenar() throws IOException {
        helloApplication.setRoot("ordenar-view");
    }

    @FXML
    public void mostrar(ActionEvent actionEvent) throws IOException {
        helloApplication.setRoot("busqueda-view");
        busquedaController.mostrarDatos();
    }

    @FXML
    public void buscar(ActionEvent actionEvent) throws IOException {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Equipo a buscar");
        textInputDialog.setHeaderText("Buscar equipo");
        textInputDialog.setContentText("Introduce el nombre del equipo a buscar");
        String resultado = "";
        while (resultado.isEmpty()) {
            Optional<String> result = textInputDialog.showAndWait();
            resultado = result.get();
        }
        helloApplication.setRoot("busqueda-view");
        busquedaController.buscarDatos(resultado);
    }

    @FXML
    public void borrar(ActionEvent actionEvent) {
    }

    @FXML
    public void restablecer(ActionEvent actionEvent) throws IOException {
        if (fichero.borrarFichero()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fichero borrado");
            alert.setHeaderText("El fichero ha sido borrado correctamente.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar el fichero");
            alert.setHeaderText("No se ha podido borrar el fichero.");
            alert.showAndWait();
        }
    }

    @FXML
    public void simular(ActionEvent actionEvent) {
    }
}
