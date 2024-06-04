package aziendaviaggi.Controllers.Agenzia;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.Utils;
import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AgenziaInsertController extends Controller {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Agenzia.setText(LoginController.CodAgenzia);
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(AgenziaInse))
            return;
        try {
            this.statement.executeUpdate("INSERT INTO PACCHETTI_TURISTICI " + "VALUES ("
                    + progressiveCode() + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Descrizione.getText()) + ", "
                    + Float.parseFloat(Prezzo.getText()) + ", "
                    + LoginController.CodAgenzia + ", "
                    + Guida.getSelectionModel().getSelectedItem() + ", "
                    + Trasporto.getSelectionModel().getSelectedItem() + ", "
                    + Alloggio.getSelectionModel().getSelectedItem() + ", "
                    + Destinazione.getSelectionModel().getSelectedItem()
                    + ")");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "agenziaApp");
    }

    protected int progressiveCode() throws SQLException {
        ResultSet res = this.statement.executeQuery("SELECT MAX(CodPacchetto) AS Max FROM PACCHETTI_TURISTICI");
        res.next();
        return res.getInt("Max") + 1;
    }

    protected void choiceBoxInit(String column, String table, ChoiceBox<String> choice) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
