package aziendaviaggi;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Utils {

    public static final void alertThrower(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static final String valueFormatter(String msg) {
        return "'" + msg + "'";
    }

    @SuppressWarnings("unchecked")
    public static final boolean checkInsert(Pane pane) {
        for (Node elem : pane.getChildren()) {
            if (elem instanceof TextField && ((TextField) elem).getText().isEmpty()) {
                alertThrower("Inserisci " + elem.getId());
                return false;
            } else if (elem instanceof ChoiceBox
                    && ((ChoiceBox<String>) elem).getSelectionModel().getSelectedItem() == null) {
                alertThrower("Seleziona " + elem.getId());
                return false;
            }
        }
        return true;
    }

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
