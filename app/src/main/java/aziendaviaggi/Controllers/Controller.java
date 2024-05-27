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
}
