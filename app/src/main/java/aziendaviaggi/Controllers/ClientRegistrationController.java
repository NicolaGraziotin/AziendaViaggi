package aziendaviaggi.Controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClientRegistrationController extends Controller {

    @FXML
    private TextField Email;

    @FXML
    private TextField CodiceFiscale;

    @FXML
    private TextField NumeroTelefono;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Cognome;

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    @FXML
    private void enter(ActionEvent event) {
        try {
            this.statement.executeUpdate("INSERT INTO CLIENTI " + "VALUES (" + valueFormatter(Email.getText())
                    + ", " + valueFormatter(CodiceFiscale.getText()) + ", " + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Cognome.getText()) + ", " +valueFormatter(NumeroTelefono.getText())+ ")");
            changeScene(event, "/fxml/login.fxml");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }
}
