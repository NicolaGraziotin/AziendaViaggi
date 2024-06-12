package aziendaviaggi;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Utility class for common functions.
 */
public class Utils {

    /**
     * Displays a confirmation dialog with the given message and returns true if the
     * user selects "Si" (Yes), false otherwise.
     *
     * @param msg the message to display in the confirmation dialog
     * @return true if the user selects "Si" (Yes), false otherwise
     */
    public static final boolean confirmThrower(final String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.getButtonTypes().setAll(new ButtonType("Si"), new ButtonType("No"));
        alert.showAndWait();
        return alert.getResult().getText().equals("Si");
    }
}
