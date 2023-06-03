package com.example.M03_Recu;

//Elements de la biblioteca per executar a JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private TextField fieldTelefon;

    @FXML
    //Métode que executa l'Exepció en cas de format incorrecte
    private void validaTelefon() {
        String telefon = fieldTelefon.getText();

        try {
            validaFormat(telefon);
            showAlert(AlertType.INFORMATION, "Numero de telèfon validat", "El numero de telèfon és vàlid");
        } catch (TelefonInvalidException e) {
            showAlert(AlertType.ERROR, "Numero de telèfon incorrecte", "El numero de telèfon no té un format vàlid" + "\n (+XX-XXX-XXXXXX)");
        }
    }

    //Ús d'expressions regulars per validar el nº de telèfon amb el format descrit
    private void validaFormat(String telefon) throws TelefonInvalidException {
        String regex = "\\+\\d{2}-\\d{3}-\\d{6}";
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
}