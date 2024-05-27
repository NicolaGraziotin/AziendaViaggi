package aziendaviaggi.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

    @FXML
    private TextField Email;

    public static String CodAgenzia;

    @FXML
    private void loginAgenzia(ActionEvent event) {
        try {
            ResultSet result = this.statement.executeQuery("SELECT Email,CodAgenzia FROM AGENZIE_VIAGGIO WHERE Email='" + Email.getText() + "'");
            if (result.next()) {
                CodAgenzia = result.getString("CodAgenzia");
                changeScene(event, "/fxml/agenziaApp.fxml");
            } else {
                alertThrower("Email non valida.");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void loginCliente(ActionEvent event) {
        try {
            if (this.statement.executeQuery("SELECT Email FROM CLIENTI WHERE Email='" + Email.getText() + "'")
                    .next()) {
                changeScene(event, "/fxml/clientApp.fxml");
            } else {
                alertThrower("Email non valida.");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void registrationAgenzia(ActionEvent event) {
        changeScene(event, "/fxml/agenziaRegistration.fxml");
    }

    @FXML
    private void registrationCliente(ActionEvent event) {
        changeScene(event, "/fxml/clientRegistration.fxml");
    }
}
