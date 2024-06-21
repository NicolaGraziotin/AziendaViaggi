package aziendaviaggi.contro;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The LoginController class is responsible for handling user interactions and
 * logic related to the login functionality.
 */
public class LoginController extends Controller {

    @FXML
    private Pane Login;

    @FXML
    private TextField Email;

    private static String CodAgenzia;

    private static String EmailCliente;

    /**
     * Handles the login action for the agency.
     * If the email is valid, it changes the scene to "AgenziaApp".
     * Otherwise, it throws an alert with the message "Email non valida!".
     * 
     * @param event The action event triggered by the login button.
     */
    @FXML
    private void loginAgenzia(final ActionEvent event) {
        if (!checkInsert(Login.getChildren())) {
            return;
        }
        try {
            ResultSet result = this.statement
                    .executeQuery(
                            "SELECT CodAgenzia FROM AGENZIE_VIAGGIO WHERE Email = " + valueFormatter(Email.getText()));
            if (result.next()) {
                CodAgenzia = result.getString("CodAgenzia");
                System.out.println(CodAgenzia + " ha eseguito l'accesso.");
                changeScene(event, "AgenziaApp");
            } else {
                alertThrower("Email non valida!");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    /**
     * Handles the login action for the client.
     * If the email is valid, it changes the scene to "ClientApp".
     * Otherwise, it throws an alert with the message "Email non valida!".
     * 
     * @param event The action event triggered by the login button.
     */
    @FXML
    private void loginCliente(final ActionEvent event) {
        if (!checkInsert(Login.getChildren())) {
            return;
        }
        try {
            ResultSet result = this.statement
                    .executeQuery("SELECT Email FROM CLIENTI WHERE Email = " + valueFormatter(Email.getText()));
            if (result.next()) {
                EmailCliente = result.getString("Email");
                System.out.println(EmailCliente + " ha eseguito l'accesso.");
                changeScene(event, "ClientApp");
            } else {
                alertThrower("Email non valida!");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    /**
     * Changes the scene to "AgenziaRegistration".
     * 
     * @param event The action event triggered by the registration button.
     */
    @FXML
    private void registrationAgenzia(final ActionEvent event) {
        changeScene(event, "AgenziaRegistration");
    }

    /**
     * Changes the scene to "ClientRegistration".
     * 
     * @param event The action event triggered by the registration button.
     */
    @FXML
    private void registrationCliente(final ActionEvent event) {
        changeScene(event, "ClientRegistration");
    }

    /**
     * Returns the code of the agency.
     * 
     * @return The code of the agency.
     */
    public static String getCodAgenzia() {
        return CodAgenzia;
    }

    /**
     * Returns the email of the client.
     * 
     * @return The email of the client.
     */
    public static String getEmailCliente() {
        return EmailCliente;
    }
}
