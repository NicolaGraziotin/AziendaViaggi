package aziendaviaggi.Controllers.Agenzia;

import java.sql.ResultSet;
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
            this.statement.executeUpdate("INSERT INTO AGENZIE_VIAGGIO " + "VALUES ("
                    + valueFormatter(Email.getText()) + ", "
                    + String.valueOf(progressiveCode()) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Sede.getText()) + ")");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    private int progressiveCode() throws SQLException {
        ResultSet res = this.statement.executeQuery("SELECT MAX(CodAgenzia) AS Max FROM AGENZIE_VIAGGIO");
        res.next();
        return res.getInt("Max") + 1;
    }
}
