package aziendaviaggi.controllers.client;

import java.sql.SQLException;

import aziendaviaggi.controllers.ControllerRegistration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * This class represents the controller for the client registration functionality.
 * It extends the ControllerRegistration class.
 */
public class ClientRegistrationController extends ControllerRegistration {

    @FXML
    private Pane ClientRegistration;

    @FXML
    private TextField CodiceFiscale;

    @FXML
    private TextField NumeroTelefono;

    @FXML
    private TextField Cognome;

    /**
     * Handles the enter button action event.
     * It checks the input fields, inserts the client data into the database,
     * and prints a success message if the registration is successful.
     * If an exception occurs, it displays an error message.
     * @param event The action event triggered by the enter button.
     */
    @FXML
    private void enter(final ActionEvent event) {
        if (!checkInsert(ClientRegistration)) {
            return;
        }
        try {
            this.statement.executeUpdate("INSERT INTO CLIENTI VALUES ("
                    + valueFormatter(Email.getText()) + ", "
                    + valueFormatter(CodiceFiscale.getText()) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Cognome.getText()) + ", "
                    + valueFormatter(NumeroTelefono.getText())
                    + ")");
            System.out.println("Cliente " + CodiceFiscale.getText() + " registrato con successo.");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
