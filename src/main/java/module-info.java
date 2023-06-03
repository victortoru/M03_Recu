module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.M03_Recu to javafx.fxml;
    exports com.example.M03_Recu;
}