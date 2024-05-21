package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class AgenziaInsertController extends ControllerFactory {
    
    @FXML
    private Pane AgenziaInse;

    @FXML
    private void enter(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/agenziaApp.fxml");
    }
}
