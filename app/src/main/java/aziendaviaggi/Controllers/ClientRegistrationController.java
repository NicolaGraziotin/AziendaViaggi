package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ClientRegistrationController extends ControllerFactory {
    
    @FXML
    private Pane ClientReg;

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/login.fxml");
    }
}
