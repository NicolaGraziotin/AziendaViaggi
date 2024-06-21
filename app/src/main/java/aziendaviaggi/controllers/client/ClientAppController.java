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

    protected static Pacchetto selectedPacchetto;

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
     * Handles the action when the "reservations" button is clicked.
     * Changes the scene to the "ClientReservations" view.
     * 
     * @param event The action event triggered by clicking the button.
     */
    @FXML
    private void reservations(final ActionEvent event) {
        changeScene(event, "ClientReservations");
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
