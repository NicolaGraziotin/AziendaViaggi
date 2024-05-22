package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController extends ControllerFactory {

    @FXML
    private void handleLogAgenzia(ActionEvent event) {
    }

    @FXML
    private void handleLogCliente(ActionEvent event) {
    }

    @FXML
    private void handleRegAgenzia(ActionEvent event) {
        changeScene(event, "/agenziaRegistration.fxml");
    }

    @FXML
    private void handleRegCliente(ActionEvent event) {
        changeScene(event, "/clientRegistration.fxml");
    }
}
