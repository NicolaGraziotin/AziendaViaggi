package aziendaviaggi.Controllers;

import java.sql.SQLException;

import aziendaviaggi.Utils;
import aziendaviaggi.Objects.Pacchetto;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AgenziaAppController extends ControllerApp {

    @FXML
    private void insert(ActionEvent event) {
        changeScene(event, "agenziaInsert");
    }

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Pacchetto> selected;
        try {
            selected = TableV.getSelectionModel().getSelectedItems();
            if (!LoginController.CodAgenzia.equals(selected.get(0).getCodAgenzia())) {
                Utils.alertThrower("Non puoi eliminare un pacchetto non inserito da te!");
                return;
            }
            if (Utils.confirmThrower("Sei sicuro di volere eliminare il pacchetto?")) {
                remove(selected);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.alertThrower("Non ci sono piu' pacchetti!");
        }
    }

    @FXML
    private void modify(ActionEvent event) {

    }

    private void remove(ObservableList<Pacchetto> selected) {
        ObservableList<Pacchetto> list = TableV.getItems();
        selected = TableV.getSelectionModel().getSelectedItems();
        selected.forEach(sel -> {
            try {
                this.statement.executeUpdate(
                        "DELETE FROM PACCHETTI_TURISTICI WHERE CodPacchetto=" + sel.getCodPacchetto());
                list.remove(sel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
