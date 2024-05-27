package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerRegistration extends Controller {

    @FXML
    protected TextField Email;

    @FXML
    protected TextField Nome;

    @FXML
    protected final void back(ActionEvent event) {
        changeScene(event, "login");
    }

}
