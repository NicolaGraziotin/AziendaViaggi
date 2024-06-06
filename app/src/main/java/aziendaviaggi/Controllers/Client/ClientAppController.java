package aziendaviaggi.controllers.client;

import aziendaviaggi.controllers.ControllerApp;
import aziendaviaggi.objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * The ClientAppController class is responsible for handling the client's actions in the application.
 * It extends the ControllerApp class and provides methods for selecting and reviewing pacchetti (packages).
 */
public class ClientAppController extends ControllerApp {

    private static Pacchetto selectedPacchetto;

    /**
     * Handles the selection of a pacchetto (package) by the client.
     * If a pacchetto is selected, it changes the scene to "ClientSelection".
     * If no pacchetto is selected, it displays an alert message.
     *
     * @param event the action event triggered by the selection
     */
    @FXML
    private void select(final ActionEvent event) {
        selectedPacchetto = TablePacchetti.getSelectionModel().getSelectedItem();
        if (selectedPacchetto != null) {
            changeScene(event, "ClientSelection");
        } else {
            alertThrower("Non hai selezionato un pacchetto!");
        }
    }

    /**
     * Handles the review of a pacchetto (package) by the client.
     * If a pacchetto is selected, it changes the scene to "ClientReview".
     * If no pacchetto is selected, it displays an alert message.
     *
     * @param event the action event triggered by the review
     */
    @FXML
    private void review(final ActionEvent event) {
        selectedPacchetto = TablePacchetti.getSelectionModel().getSelectedItem();
        if (selectedPacchetto != null) {
            changeScene(event, "ClientReview");
        } else {
            alertThrower("Non hai selezionato un pacchetto!");
        }
    }

    /**
     * Returns the currently selected pacchetto.
     *
     * @return the selected pacchetto
     */
    public static Pacchetto getActual() {
        return selectedPacchetto;
    }
}
