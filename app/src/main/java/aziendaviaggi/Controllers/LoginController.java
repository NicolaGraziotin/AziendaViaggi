package aziendaviaggi.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import aziendaviaggi.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController extends Controller {

    @FXML
    private Pane LoginPane;

    @FXML
    private TextField Email;

    public static String CodAgenzia;

    @FXML
    private void loginAgenzia(ActionEvent event) {
        if (!Utils.checkInsert(LoginPane))
            return;
        try {
            ResultSet result = this.statement
                    .executeQuery("SELECT Email,CodAgenzia FROM AGENZIE_VIAGGIO WHERE Email='" + Email.getText() + "'");
            if (result.next()) {
                CodAgenzia = result.getString("CodAgenzia");
                changeScene(event, "agenziaApp");
            }
        } catch (SQLException e) {
            Utils.alertThrower(e.getMessage());
        }
    }

    @FXML
    private void loginCliente(ActionEvent event) {
        if (!Utils.checkInsert(LoginPane))
            return;
        try {
            if (this.statement.executeQuery("SELECT Email FROM CLIENTI WHERE Email='" + Email.getText() + "'")
                    .next()) {
                changeScene(event, "clientApp");
            }
        } catch (SQLException e) {
            Utils.alertThrower(e.getMessage());
        }
    }

    @FXML
    private void registrationAgenzia(ActionEvent event) {
        changeScene(event, "agenziaRegistration");
    }

    @FXML
    private void registrationCliente(ActionEvent event) {
        changeScene(event, "clientRegistration");
    }
}
