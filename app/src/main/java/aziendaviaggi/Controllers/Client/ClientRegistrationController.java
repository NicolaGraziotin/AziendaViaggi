package aziendaviaggi.Controllers.Client;

import java.sql.SQLException;

import aziendaviaggi.Controllers.ControllerRegistration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientRegistrationController extends ControllerRegistration {

    @FXML
    private Pane ClientRegistration;

    @FXML
    private TextField CodiceFiscale;

    @FXML
    private TextField NumeroTelefono;

    @FXML
    private TextField Cognome;

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(ClientRegistration))
            return;
        try {
            this.statement.executeUpdate("INSERT INTO CLIENTI " + "VALUES ("
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
