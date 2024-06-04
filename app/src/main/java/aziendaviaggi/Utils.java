package aziendaviaggi;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Utils {

    

    public static final boolean confirmThrower(String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.getButtonTypes().setAll(new ButtonType("Si"), new ButtonType("No"));
        alert.showAndWait();
        return alert.getResult().getText().equals("Si");
    }
}
