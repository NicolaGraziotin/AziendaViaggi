package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import java.util.stream.Collectors;

public class LoginController {

    @FXML
    private Pane LoginPane;

    @FXML
    private void handleLogAgenzia(ActionEvent event) {
        System.out.println(LoginPane.getChildren().stream()
                                                    .map(c -> c.getId())
                                                    .collect(Collectors.toList()));
    }

    @FXML
    private void handleLogCliente(ActionEvent event) {
        System.out.println(LoginPane.getChildren().stream()
                                                    .map(c -> c.getId())
                                                    .collect(Collectors.toList()));
    }

    @FXML
    private void handleRegAgenzia(ActionEvent event) {
    }

    @FXML
    private void handleRegCliente(ActionEvent event) {
    }
}
