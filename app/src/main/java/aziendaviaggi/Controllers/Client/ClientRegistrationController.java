package aziendaviaggi.Controllers.Client;

import java.sql.SQLException;

import aziendaviaggi.Utils;
import aziendaviaggi.Controllers.ControllerRegistration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientRegistrationController extends ControllerRegistration {

    @FXML
    private Pane ClientRegi;

    @FXML
    private TextField CodiceFiscale;

    @FXML
    private TextField NumeroTelefono;

    @FXML
    private TextField Cognome;

    @FXML
    private void enter(ActionEvent event) {
        if (!Utils.checkInsert(ClientRegi))
            return;
        try {
            this.statement.executeUpdate("INSERT INTO CLIENTI " + "VALUES (" + Utils.valueFormatter(Email.getText())
                    + ", " + Utils.valueFormatter(CodiceFiscale.getText()) + ", " + Utils.valueFormatter(Nome.getText())
                    + ", "
                    + Utils.valueFormatter(Cognome.getText()) + ", " + Utils.valueFormatter(NumeroTelefono.getText())
                    + ")");
            back(event);
        } catch (SQLException e) {
            Utils.alertThrower(e.getMessage());
        }
    }
}
