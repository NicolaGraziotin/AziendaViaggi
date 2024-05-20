package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class AgenziaAppController extends ControllerFactory {

    @FXML
    private Pane AgenziaApp;

    @FXML
    private void insert(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) {

    }

    @FXML
    private void modify(ActionEvent event) {

    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/agenziaRegistration.fxml");
    }

}
