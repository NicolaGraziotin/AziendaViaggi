package aziendaviaggi.Controllers;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.SQLException;

import aziendaviaggi.SQLDatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AgenziaInsertController extends ControllerFactory {

    @FXML
    private Pane AgenziaInse;

    @FXML
    private TextField Descrizione;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Prezzo;

    @FXML
    private ChoiceBox<String> Alloggio;

    @FXML
    private ChoiceBox<String> Destinazione;

    @FXML
    private ChoiceBox<String> Guida;

    @FXML
    private ChoiceBox<String> Trasporto;

    @FXML
    private TextField Agenzia;

    private Statement statement = SQLDatabaseConnection.getStatement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Agenzia.setText(LoginController.CodAgenzia);
        Alloggio.getItems().addAll("0", "1");
        Destinazione.getItems().addAll("0", "1");
        Guida.getItems().addAll("" ,"0", "1");
        Trasporto.getItems().addAll("0", "1");
    }

    @FXML
    private void enter(ActionEvent event) {
        try {
            System.out.println("VALUES ("
            + progressiveCode() + ", "
            + valueFormatter(Nome.getText()) + ", "
            + valueFormatter(Descrizione.getText()) + ", "
            + Float.parseFloat(Prezzo.getText()) + ", "
            + Integer.parseInt(LoginController.CodAgenzia) + ", "
            + Trasporto.getSelectionModel().getSelectedItem() + ", "
            + Alloggio.getSelectionModel().getSelectedItem() + ", "
            + Destinazione.getSelectionModel().getSelectedItem()
            + ")");
            this.statement.executeUpdate("INSERT INTO PACCHETTI_TURISTICI" + "VALUES ("
                    + progressiveCode() + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Descrizione.getText()) + ", "
                    + Float.parseFloat(Prezzo.getText()) + ", "
                    + Integer.parseInt(LoginController.CodAgenzia) + ", "
                    + Trasporto.getSelectionModel().getSelectedItem() + ", "
                    + Alloggio.getSelectionModel().getSelectedItem() + ", "
                    + Destinazione.getSelectionModel().getSelectedItem()
                    + ")");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/agenziaApp.fxml");
    }
    
    private int progressiveCode() throws SQLException {
        ResultSet res = this.statement.executeQuery("SELECT MAX(CodPacchetto) AS Max FROM PACCHETTI_TURISTICI");
        res.next();
        return res.getInt("Max") + 1;
    }
}
