package aziendaviaggi;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;

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

    /**
     * Resizes the scene to a target width while maintaining the aspect ratio of the content.
     *
     * @param root The root node of the scene.
     * @return The resized scene.
     */
    public static final Scene resizeScene(final Parent root) {
        // Ottieni la larghezza dello schermo
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        
        // Calcola la larghezza desiderata (3/4 della larghezza dello schermo)
        double targetWidth = screenWidth * 0.75;
        
        // Ottieni le dimensioni originali del contenuto FXML
        double originalWidth = root.prefWidth(-1);  // Usa -1 per prendere il valore preferito
        double originalHeight = root.prefHeight(-1);

        // Calcola il fattore di scala
        double scaleFactor = targetWidth / originalWidth;

        // Crea una trasformazione di scala
        Scale scale = new Scale(scaleFactor, scaleFactor);

        // Applica la scala al nodo radice
        root.getTransforms().add(scale);

        // Calcola le nuove dimensioni della scena
        double newWidth = originalWidth * scaleFactor;
        double newHeight = originalHeight * scaleFactor;

        // Crea la scena con le nuove dimensioni
        return new Scene(root, newWidth, newHeight);
    }
}
