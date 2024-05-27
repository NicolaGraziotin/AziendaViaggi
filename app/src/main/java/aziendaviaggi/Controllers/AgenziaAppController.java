package aziendaviaggi.Controllers;

import aziendaviaggi.Objects.Pacchetto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AgenziaAppController extends ControllerApp {

    @FXML
    private void insert(ActionEvent event) {
        changeScene(event, "/fxml/agenziaInsert.fxml");
    }

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Pacchetto> selected, list;
        try {
            list = TableV.getItems();
            selected = TableV.getSelectionModel().getSelectedItems();
            for (Pacchetto pacchetto : selected) {
                list.remove(pacchetto);
            }
        } catch (Exception e) {
            alertThrower("Non ci sono piu' pacchetti!");
        }
    }

    @FXML
    private void modify(ActionEvent event) {

    }
}
