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
        ObservableList<Pacchetto> selected = TableV.getSelectionModel().getSelectedItems();
        if (checkCode(selected) && Utils.confirmThrower("Sei sicuro di volere eliminare il pacchetto?")) {
            remove(selected);
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        ObservableList<Pacchetto> selected = TableV.getSelectionModel().getSelectedItems();
        checkCode(selected);
    }

    private void remove(ObservableList<Pacchetto> selected) {
        ObservableList<Pacchetto> list = TableV.getItems();
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

    private boolean checkCode(ObservableList<Pacchetto> selected) {
        try {
            if (!LoginController.CodAgenzia.equals(selected.get(0).getCodAgenzia())) {
                Utils.alertThrower("Non puoi eliminare un pacchetto non inserito da te!");
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            Utils.alertThrower("Non hai selezionato nessun pacchetto!");
            return false;
        } catch (Exception e) {
            Utils.alertThrower("Non ci sono piu' pacchetti!");
            return false;
        }
        return true;
    }
}
