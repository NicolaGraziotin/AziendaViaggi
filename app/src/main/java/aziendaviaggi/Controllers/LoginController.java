package aziendaviaggi.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController extends Controller {

    @FXML
    private Pane Login;

    @FXML
    private TextField Email;

    private static String CodAgenzia;

    private static String EmailCliente;

    @FXML
    private void loginAgenzia(ActionEvent event) {
        if (!checkInsert(Login))
            return;
        try {
            ResultSet result = this.statement
                    .executeQuery("SELECT Email,CodAgenzia FROM AGENZIE_VIAGGIO WHERE Email='" + Email.getText() + "'");
            if (result.next()) {
                CodAgenzia = result.getString("CodAgenzia");
                changeScene(event, "AgenziaApp");
            } else {
                alertThrower("Email non valida!");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void loginCliente(ActionEvent event) {
        if (!checkInsert(Login))
            return;
        try {
            ResultSet result = this.statement.executeQuery("SELECT Email FROM CLIENTI WHERE Email='" + Email.getText() + "'");
            if (result.next()) {
                EmailCliente = result.getString("Email");
                changeScene(event, "ClientApp");
            } else {
                alertThrower("Email non valida!");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void registrationAgenzia(ActionEvent event) {
        changeScene(event, "AgenziaRegistration");
    }

    @FXML
    private void registrationCliente(ActionEvent event) {
        changeScene(event, "ClientRegistration");
    }

    public static String getCodAgenzia() {
        return CodAgenzia;
    }

    public static String getEmailCliente() {
        return EmailCliente;
    }
}
