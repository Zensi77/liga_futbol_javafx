package com.jer.liga_futbol;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;

public class CrearController {
    // Instancia de la clase HelloApplication
    private HelloApplication helloApplication = new HelloApplication();
    private  ficheroFutbol fichero = new ficheroFutbol();
    @FXML
    private TextArea nameTeam, gamesPlayed, playsWin, playsLost;
    @FXML
    Button buttonModificar;
    @FXML
    Label alertMod;

    @FXML
    public void insertarDatos() throws IOException {
        String name = nameTeam.getText();
        String games = gamesPlayed.getText();
        String win = playsWin.getText();
        String lost = playsLost.getText();

        try {
            int gamesInt = Integer.parseInt(games);
            int winInt = Integer.parseInt(win);
            int lostInt = Integer.parseInt(lost);

            if (name.isEmpty() || games.isEmpty() || win.isEmpty() || lost.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Parámetros incorrectos", "Todos los campos son obligatorios.");
                return;
            }

            if (gamesInt < winInt + lostInt) {
                showAlert(Alert.AlertType.ERROR, "Parámetros incorrectos", "El número de partidos jugados debe ser mayor o igual que la suma de partidos ganados y perdidos.");
                return;
            }

            int playsDraft = gamesInt- winInt + lostInt;
            fichero.guardar(name, gamesInt, winInt, playsDraft);
            showAlert(Alert.AlertType.CONFIRMATION, "Operación exitosa", "Se han guardado los datos del equipo correctamente.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error de formato", "Ingrese valores numéricos válidos para los campos correspondientes.");
        }
    }
    @FXML
    public void modificarDatos() throws IOException {
        try {
            String name = nameTeam.getText();
            String games = gamesPlayed.getText();
            String win = playsWin.getText();
            String lost = playsLost.getText();

            if (games.isEmpty() || win.isEmpty() || lost.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Parámetros incorrectos", "Si no desea modificar un campo, establezca el valor a 0.");
                return;
            }

            int gamesInt = Integer.parseInt(games);
            int winInt = Integer.parseInt(win);
            int lostInt = Integer.parseInt(lost);

            if (name.isEmpty() || gamesInt < (winInt + lostInt) || gamesInt < winInt || gamesInt < lostInt) {
                showAlert(Alert.AlertType.ERROR, "Parámetros incorrectos", "Los parámetros no son válidos.");
                return;
            }

            if (fichero.buscarDatos(name).isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Equipo no encontrado", "No se ha encontrado el equipo " + name);
                return;
            }

            int result = fichero.modify(name, new int[]{gamesInt, winInt, lostInt});
            if (result == 1) {
                showAlert(Alert.AlertType.CONFIRMATION, "Operación correcta", "Se ha modificado el equipo " + name);
            } else {
                showAlert(Alert.AlertType.ERROR, "Operación incorrecta", "No se ha podido modificar el equipo " + name);
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error de formato", "Ingrese valores numéricos válidos para los campos correspondientes.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
