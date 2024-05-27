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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller implements Initializable {

    protected Statement statement = SQLDatabaseConnection.getStatement();

    @Override
    public void initialize(URL location, ResourceBundle resources) { };
    
    protected void changeScene(ActionEvent event, String url) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void alertThrower(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    protected String valueFormatter(String msg) {
        return "'" + msg + "'";
    }
}
