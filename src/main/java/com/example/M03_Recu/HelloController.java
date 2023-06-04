package com.example.M03_Recu;

//Elements de la biblioteca per executar a JavaFX
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private TextField fieldTelefon;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private void initialize() {
        exitMenuItem.setOnAction(event -> sortirApp());
        helpMenuItem.setOnAction(event -> helpOpcio());
    }

    @FXML
    //Métode que executa l'Exepció en cas de format incorrecte
    private void validaTelefon() {
        String telefon = fieldTelefon.getText();

        try {
            validaFormat(telefon);
            showAlert(AlertType.INFORMATION, "Numero de telèfon validat", "El numero de telèfon és vàlid");
        } catch (TelefonInvalidException e) {
            showAlert(AlertType.ERROR, "Numero de telèfon incorrecte", "El numero de telèfon no té un format vàlid" + "\n (+XX-XXXXXXXXX)");
        }
    }

    //Ús d'expressions regulars per validar el nº de telèfon amb el format descrit
    private void validaFormat(String telefon) throws TelefonInvalidException {
        String regex = "\\+\\d{2} \\d{3}\\d{6}";
        if (!Pattern.matches(regex, telefon)) {
            throw new TelefonInvalidException();
        }
    }
    //Exepció creada que mostra un missatge d'error
    public class TelefonInvalidException extends Exception {
        public TelefonInvalidException() {
            super("Telèfon incorrecte");
        }
    }

    //Métode per obrir finestres d'informació o d'error en cas de sintaxi incorrecta
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void sortirApp() {
        Platform.exit();
    }

    private void helpOpcio() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajuda");
        alert.setHeaderText("Informació adicional");
        alert.setContentText("Aquesta app valida el format d'un numero de telèfon.\n" +
                "El numero de telèfon ha de tenir el format +XX-XXXXXXXXX.\n" +
                "Per exemple: +34-123456789 es un numero de telèfon vèlid.\n\n" +
                "Per comprovar el format del nº escrit, s'haurà d'introduïr al recuadre facilitat.\n" +
                "Projecte realitzat per Víctor Tobaruela");
        alert.showAndWait();
    }
}