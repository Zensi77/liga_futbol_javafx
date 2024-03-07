/**
 * Sample Skeleton for 'ordenar-view.fxml' Controller Class
 */

package com.jer.liga_futbol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdenarController {
    ficheroFutbol fichero = new ficheroFutbol();

    @FXML
    private ComboBox<String> parametro;
    @FXML
    private ComboBox<String> ordenacion;
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
        // Introduciomos los valores de los ComboBox
        ObservableList<String> boxparam = FXCollections.observableArrayList("Nombre", "Partidos jugados", "Partidos ganados", "Partidos perdidos", "Partidos empatados", "Puntos");
        parametro.setItems(boxparam);
        ObservableList<String> boxord = FXCollections.observableArrayList("Ascendente", "Descendente");
        ordenacion.setItems(boxord);
        // Asignamos las columnas a los atributos de la clase equipo
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colplays.setCellValueFactory(new PropertyValueFactory<>("partidosJugados"));
        colplaysWin.setCellValueFactory(new PropertyValueFactory<>("partidosGanados"));
        colplaysLost.setCellValueFactory(new PropertyValueFactory<>("partidosPerdidos"));
        colplaysDraw.setCellValueFactory(new PropertyValueFactory<>("partidosEmpatados"));
        colptos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
    }

    public void ordenar(ActionEvent actionEvent) {
        int resultado = 0;
        String param = parametro.getValue();
        String ord = ordenacion.getValue();
        if (fichero.mostrarDatos().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No hay equipos registrados");
            alert.showAndWait();

        }else if (param == null || ord == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Por defecto se ordenara por nombre de forma descendente por puntos");
            alert.showAndWait();
            fichero.ordenacion(0, 2);
        } else {
            switch (param) {
                case "Nombre":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(0, 1);
                    } else {
                        resultado = fichero.ordenacion(0, 2);
                    }
                    break;
                case "Partidos jugados":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(1, 1);
                    } else {
                        resultado = fichero.ordenacion(1, 2);
                    }
                    break;
                case "Partidos ganados":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(2, 1);
                    } else {
                        resultado = fichero.ordenacion(2, 2);
                    }
                    break;
                case "Partidos perdidos":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(3, 1);
                    } else {
                        resultado = fichero.ordenacion(3, 2);
                    }
                    break;
                case "Partidos empatados":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(4, 1);
                    } else {
                        resultado = fichero.ordenacion(4, 2);
                    }
                    break;
                case "Puntos":
                    if (ord.equals("Ascendente")) {
                        resultado = fichero.ordenacion(5, 1);
                    } else {
                        resultado = fichero.ordenacion(5, 2);
                    }
                    break;
            }
            if (resultado == 1) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ordenado con exito por " + param + " de forma " + ord);
                alert.showAndWait();
            }

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
}
