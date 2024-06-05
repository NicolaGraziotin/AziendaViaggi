package aziendaviaggi.Controllers.Agenzia;

import java.sql.SQLException;

import aziendaviaggi.Controllers.ControllerRegistration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AgenziaRegistrationController extends ControllerRegistration {

    @FXML
    private Pane AgenziaRegi;

    @FXML
    private TextField Sede;

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(AgenziaRegi))
            return;
        try {
            String code = progressiveCode("CodAgenzia", "AGENZIE_VIAGGIO", "AG");
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
