package aziendaviaggi.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientRegistrationController {
    
    @FXML
    private Pane ClientReg;

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/login.fxml");
    }

    private void changeScene(ActionEvent event, String url) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
