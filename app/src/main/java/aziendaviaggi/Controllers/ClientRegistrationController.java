package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClientRegistrationController extends ControllerFactory {

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/login.fxml");
    }

    @FXML
    private void enter(ActionEvent event) {
        changeScene(event, "/clientApp.fxml");
    }
}
