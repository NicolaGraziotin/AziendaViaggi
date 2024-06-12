package aziendaviaggi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * This class represents the controller for the registration view.
 * It extends the base Controller class.
 */
public class ControllerRegistration extends Controller {

    @FXML
    protected TextField Email;

    @FXML
    protected TextField Nome;

    /**
     * Handles the back button action event.
     * Changes the scene to the "Login" view.
     * 
     * @param event The action event triggered by the back button.
     */
    @FXML
    protected final void back(final ActionEvent event) {
        changeScene(event, "Login");
    }

}
