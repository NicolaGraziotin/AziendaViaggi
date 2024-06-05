package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Controllers.ControllerApp;
import aziendaviaggi.Objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClientAppController extends ControllerApp {

    private static Pacchetto selectedPacchetto;

    @FXML
    private void select(ActionEvent event) {
        selectedPacchetto = TableV.getSelectionModel().getSelectedItem();
        if (selectedPacchetto != null) {
            changeScene(event, "clientSelection");
        } else {
            alertThrower("Non hai selezionato un pacchetto!");
        }
    }

    @FXML
    private void review(ActionEvent event) {
        selectedPacchetto = TableV.getSelectionModel().getSelectedItem();
        if (selectedPacchetto != null) {
            changeScene(event, "clientReview");
        } else {
            alertThrower("Non hai selezionato un pacchetto!");
        }
    }

    public static Pacchetto getActual() {
        return selectedPacchetto;
    }
}
