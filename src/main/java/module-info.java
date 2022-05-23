module com.example.helbpark {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helbpark to javafx.fxml;
    exports com.example.helbpark;
}