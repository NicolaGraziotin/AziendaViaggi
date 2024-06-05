package aziendaviaggi.Controllers.Agenzia;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Pane AgenziaInsert;

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
        Agenzia.setText(LoginController.getCodAgenzia());
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(AgenziaInsert))
            return;
        try {
            String codPacchetto = progressiveCode("CodPacchetto", "PACCHETTI_TURISTICI", "P");
            this.statement.executeUpdate("INSERT INTO PACCHETTI_TURISTICI " + "VALUES ("
                    + valueFormatter(codPacchetto) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Descrizione.getText()) + ", "
                    + valueFormatter(Prezzo.getText()) + ", "
                    + valueFormatter(LoginController.getCodAgenzia()) + ", "
                    + guidaCheck(Guida.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Trasporto.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Alloggio.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Destinazione.getSelectionModel().getSelectedItem())
                    + ")");
            System.out.println(codPacchetto);
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "AgenziaApp");
    }

    protected String guidaCheck(String guida) {
        return guida == "NULL" ? guida : valueFormatter(guida);
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
