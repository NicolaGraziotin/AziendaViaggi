package aziendaviaggi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientRegistration.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
