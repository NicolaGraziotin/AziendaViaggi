package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends ControllerFactory {

    @FXML
    private TextField Email;

    @FXML
    private void handleLogAgenzia(ActionEvent event) {
        emailCheck(event, "/fxml/agenziaApp.fxml");
    }

    @FXML
    private void handleLogCliente(ActionEvent event) {
        emailCheck(event, "/fxml/clientApp.fxml");
    }

    @FXML
    private void handleRegAgenzia(ActionEvent event) {
        changeScene(event, "/fxml/agenziaRegistration.fxml");
    }

    @FXML
    private void handleRegCliente(ActionEvent event) {
        changeScene(event, "/fxml/clientRegistration.fxml");
    }

    private void emailCheck(ActionEvent event, String scene) {
        if (Email.getText().isEmpty()) {
            alertThrower("Inserire " + Email.getId() + " valida!");
        } else {
            changeScene(event, scene);
        }
    }
}
