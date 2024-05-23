package aziendaviaggi.Controllers;

import java.sql.SQLException;
import java.sql.Statement;

import aziendaviaggi.SQLDatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends ControllerFactory {

    @FXML
    private TextField Email;

    private Statement statement = SQLDatabaseConnection.getStatement();

    @FXML
    private void loginAgenzia(ActionEvent event) {
        loginCheck(event, "/fxml/agenziaApp.fxml", "AGENZIE_VIAGGIO");
    }

    @FXML
    private void loginCliente(ActionEvent event) {
        loginCheck(event, "/fxml/clientApp.fxml", "CLIENTI");
    }

    @FXML
    private void registrationAgenzia(ActionEvent event) {
        changeScene(event, "/fxml/agenziaRegistration.fxml");
    }

    @FXML
    private void registrationCliente(ActionEvent event) {
        changeScene(event, "/fxml/clientRegistration.fxml");
    }

    private void loginCheck (ActionEvent event, String scene, String table) {
        try {
            if (this.statement.executeQuery("SELECT Email FROM "+table+" WHERE Email='" + Email.getText() + "'")
                    .next()) {
                changeScene(event, scene);
            } else {
                alertThrower("Email non valida.");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }
}
