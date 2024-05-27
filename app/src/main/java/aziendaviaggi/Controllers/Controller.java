package aziendaviaggi.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

import aziendaviaggi.SQLDatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller implements Initializable {

    protected Statement statement = SQLDatabaseConnection.getStatement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    };

    protected final void changeScene(ActionEvent event, String panel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + panel + ".fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final void alertThrower(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    protected final String valueFormatter(String msg) {
        return "'" + msg + "'";
    }

    @SuppressWarnings("unchecked")
    protected final boolean checkInsert(Pane pane) {
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
}
