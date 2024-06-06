package aziendaviaggi.controllers.agenzia;

import java.sql.SQLException;

import aziendaviaggi.controllers.ControllerRegistration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * This class represents the controller for the Agenzia Registration view.
 * It extends the ControllerRegistration class.
 */
public class AgenziaRegistrationController extends ControllerRegistration {

    @FXML
    private Pane AgenziaRegistration;

    @FXML
    private TextField Sede;

    /**
     * This method is called when the enter button is clicked.
     * It performs the registration of an Agenzia by inserting the data into the database.
     * If the data is successfully inserted, it prints a success message and goes back to the previous view.
     * If an exception occurs, it throws an alert with the error message.
     * @param event The ActionEvent triggered by clicking the enter button.
     */
    @FXML
    private void enter(final ActionEvent event) {
        if (!checkInsert(AgenziaRegistration)) {
            return;
        }
        try {
            final String code = progressiveCode("CodAgenzia", "AGENZIE_VIAGGIO", "AG");
            this.statement.executeUpdate("INSERT INTO AGENZIE_VIAGGIO " + "VALUES ("
                    + valueFormatter(Email.getText()) + ", "
                    + valueFormatter(code) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Sede.getText()) + ")");
            System.out.println("Agenzia " + code + " registrata con successo.");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
