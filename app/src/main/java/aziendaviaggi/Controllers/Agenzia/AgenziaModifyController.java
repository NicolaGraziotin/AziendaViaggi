package aziendaviaggi.controllers.agenzia;

import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.SQLException;

/**
 * The AgenziaModifyController class is responsible for handling the modification of a package in the travel agency application.
 * It extends the AgenziaInsertController class.
 * This controller is associated with the AgenziaModify.fxml file.
 */
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

    /**
     * Initializes the controller.
     * It sets up the choice boxes and populates them with data from the database.
     * It also sets the initial values for the text fields based on the selected package.
     * 
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);

        final Pacchetto selected = AgenziaAppController.getSelectedPacchetto();
        Nome.setText(selected.getNome());
        Descrizione.setText(selected.getDescrizione());
        Prezzo.setText(selected.getPrezzo());
        Agenzia.setText(LoginController.getCodAgenzia());
        Trasporto.getSelectionModel().select(selected.getCodTrasporto());
        Guida.getSelectionModel().select(selected.getCodGuida());
        Alloggio.getSelectionModel().select(selected.getCodAlloggio());
        Destinazione.getSelectionModel().select(selected.getCodDestinazione());
    }

    /**
     * Handles the back button action.
     * It changes the scene to the AgenziaApp scene.
     * 
     * @param event The event that triggered the action.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "AgenziaApp");
    }

    /**
     * Handles the enter button action.
     * It checks if the input is valid, updates the package in the database, and navigates back to the AgenziaApp scene.
     * 
     * @param event The event that triggered the action.
     */
    @FXML
    private void enter(final ActionEvent event) {
        if (!checkInsert(AgenziaModify)) {
            return;
        }
        try {
            final String codPacchetto = AgenziaAppController.getSelectedPacchetto().getCodPacchetto();
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
