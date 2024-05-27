package aziendaviaggi.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AgenziaRegistrationController extends Controller {

    @FXML
    private TextField Email;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Sede;

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    @FXML
    private void enter(ActionEvent event) {
        try {
            this.statement.executeUpdate("INSERT INTO AGENZIE_VIAGGIO " + "VALUES ("
                    + valueFormatter(Email.getText()) + ", "
                    + String.valueOf(progressiveCode())+ ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Sede.getText()) + ")");
            changeScene(event, "/fxml/login.fxml");
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
