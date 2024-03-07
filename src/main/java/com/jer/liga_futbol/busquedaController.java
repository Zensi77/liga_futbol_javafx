package com.jer.liga_futbol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class busquedaController {
    ficheroFutbol fichero = new ficheroFutbol();

    @FXML
    private TableView<equipos> tblequipos; // Value injected by FXMLLoader

    @FXML // fx:id="colname"
    private TableColumn<equipos, String> colname; // Value injected by FXMLLoader

    @FXML // fx:id="colplays"
    private TableColumn<equipos, Integer> colplays; // Value injected by FXMLLoader

    @FXML // fx:id="colplaysDraw"
    private TableColumn<equipos, Integer> colplaysDraw; // Value injected by FXMLLoader

    @FXML // fx:id="colplaysLost"
    private TableColumn<equipos, Integer> colplaysLost; // Value injected by FXMLLoader

    @FXML // fx:id="colplaysWin"
    private TableColumn<equipos, Integer> colplaysWin; // Value injected by FXMLLoader

    @FXML // fx:id="colptos"
    private TableColumn<equipos, Integer> colptos; // Value injected by FXMLLoader

    @FXML
    private ObservableList<equipos> equipo = FXCollections.observableArrayList(); // Lista de equipos

    @FXML
    public void initialize() {
        // Asignamos las columnas a los atributos de la clase equipo
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colplays.setCellValueFactory(new PropertyValueFactory<>("partidosJugados"));
        colplaysWin.setCellValueFactory(new PropertyValueFactory<>("partidosGanados"));
        colplaysLost.setCellValueFactory(new PropertyValueFactory<>("partidosPerdidos"));
        colplaysDraw.setCellValueFactory(new PropertyValueFactory<>("partidosEmpatados"));
        colptos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
    }

    @FXML
    public void mostrarDatos() {
        String datos = fichero.mostrarDatos();

        if (datos.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fichero vacio");
            alert.setHeaderText("No hay equipos registrados");
            alert.showAndWait();

        } else {
            tblequipos.refresh();
            String equipos = fichero.mostrarDatos();
            String[] lineas = equipos.split("\n");
            for (String a : lineas) {
                String[] nombre = a.split(";");
                equipo.add(new equipos(nombre[0], Integer.parseInt(nombre[1]), Integer.parseInt(nombre[2]),
                        Integer.parseInt(nombre[3]), Integer.parseInt(nombre[4]), Integer.parseInt(nombre[5])));
            }
            tblequipos.setItems(equipo);
        }
    }

    @FXML
    public void buscarDatos(String name) {
        tblequipos.refresh();
        String datos = fichero.mostrarDatos();
        String[] lineas = datos.split("\n");
        for (String a : lineas) {
            String[] nombre = a.split(";");
            equipo.add(new equipos(nombre[0], Integer.parseInt(nombre[1]), Integer.parseInt(nombre[2]),
                    Integer.parseInt(nombre[3]), Integer.parseInt(nombre[4]), Integer.parseInt(nombre[5])));
        }
        tblequipos.setItems(equipo);

    }
}
