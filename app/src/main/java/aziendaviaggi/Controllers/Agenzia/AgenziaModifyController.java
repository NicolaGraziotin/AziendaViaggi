package aziendaviaggi.Controllers.Agenzia;

import aziendaviaggi.Controllers.LoginController;
import aziendaviaggi.Objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.SQLException;

public class AgenziaModifyController extends AgenziaInsertController {

    @FXML
    private Pane AgenziaModify;

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
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);

        Pacchetto selected = AgenziaAppController.getSelectedPacchetto();
        Nome.setText(selected.getNome());
        Descrizione.setText(selected.getDescrizione());
        Prezzo.setText(selected.getPrezzo());
        Agenzia.setText(LoginController.getCodAgenzia());
        Trasporto.getSelectionModel().select(selected.getCodTrasporto());
        Guida.getSelectionModel().select(selected.getCodGuida());
        Alloggio.getSelectionModel().select(selected.getCodAlloggio());
        Destinazione.getSelectionModel().select(selected.getCodDestinazione());
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "AgenziaApp");
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(AgenziaModify))
            return;
        try {
            String codPacchetto = AgenziaAppController.getSelectedPacchetto().getCodPacchetto();
            this.statement.executeUpdate("UPDATE PACCHETTI_TURISTICI SET "
                    + "Nome = " + valueFormatter(Nome.getText())
                    + ", Descrizione = " + valueFormatter(Descrizione.getText())
                    + ", Prezzo = " + valueFormatter(Prezzo.getText())
                    + ", CodAgenzia = " + valueFormatter(LoginController.getCodAgenzia())
                    + ", CodGuida = " + guidaCheck(Guida.getSelectionModel().getSelectedItem())
                    + ", CodTrasporto = " + valueFormatter(Trasporto.getSelectionModel().getSelectedItem())
                    + ", CodAlloggio = " + valueFormatter(Alloggio.getSelectionModel().getSelectedItem())
                    + ", CodDestinazione = " + valueFormatter(Destinazione.getSelectionModel().getSelectedItem())
                    + " WHERE CodPacchetto = " + valueFormatter(codPacchetto));
            System.out.println("Pacchetto " + codPacchetto + " modificato con successo.");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
