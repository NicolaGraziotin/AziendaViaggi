package aziendaviaggi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private void handleLogAgenzia(ActionEvent event) {
        System.out.println(inputField.getText());
    }

    @FXML
    private void handleLogCliente(ActionEvent event) {
        System.out.println(inputField.getText());
    }

    @FXML
    private void handleRegAgenzia(ActionEvent event) {
        System.out.println("regA");
    }

    @FXML
    private void handleRegCliente(ActionEvent event) {
        
    }
}
