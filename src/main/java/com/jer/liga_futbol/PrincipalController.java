package com.jer.liga_futbol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
;
import java.io.*;


public class PrincipalController {
    // Instancia de la clase HelloApplication
    private HelloApplication helloApplication = new HelloApplication();


    // Inputs de la vista crear-view

    @FXML
    private void initialize() {
        //Inicializamos el fichero
        File fichero = new File("src/main/java/com/jer/liga_futbol/liga.txt");
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }



}