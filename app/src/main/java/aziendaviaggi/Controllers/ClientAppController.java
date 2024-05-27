package aziendaviaggi.Controllers;

import aziendaviaggi.Utils;
import aziendaviaggi.Objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClientAppController extends ControllerApp {

    public static Pacchetto actual;

    @FXML
    private void select(ActionEvent event) {
        actual = TableV.getSelectionModel().getSelectedItem();
        if (actual != null) {
            changeScene(event, "clientSelection");
        } else {
            Utils.alertThrower("Non hai selezionato un pacchetto!");
        }
    }
}
