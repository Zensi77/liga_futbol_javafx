module com.jer.liga_futbol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jer.liga_futbol to javafx.fxml;
    exports com.jer.liga_futbol;
}