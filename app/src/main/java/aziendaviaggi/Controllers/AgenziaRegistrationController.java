package aziendaviaggi.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AgenziaRegistrationController extends ControllerRegistration {

    @FXML
    private TextField Sede;

    @FXML
    private void enter(ActionEvent event) {
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
