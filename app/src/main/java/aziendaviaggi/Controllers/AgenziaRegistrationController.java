package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AgenziaRegistrationController extends ControllerFactory {

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    @FXML
    private void enter(ActionEvent event) {
        changeScene(event, "/fxml/agenziaApp.fxml");
    }
}
