package aziendaviaggi.Controllers.Agenzia;

import java.sql.SQLException;

import aziendaviaggi.Utils;
import aziendaviaggi.Controllers.ControllerApp;
import aziendaviaggi.Controllers.LoginController;
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
        Pacchetto selected = TableV.getSelectionModel().getSelectedItem();
        if (checkCode(selected, "eliminare") && Utils.confirmThrower("Sei sicuro di volere eliminare il pacchetto?")) {
            remove(selected);
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        Pacchetto selected = TableV.getSelectionModel().getSelectedItem();
        checkCode(selected, "modificare");
    }

    private void remove(Pacchetto selected) {
        ObservableList<Pacchetto> list = TableV.getItems();
        try {
            this.statement.executeUpdate(
                    "DELETE FROM PACCHETTI_TURISTICI WHERE CodPacchetto=" + selected.getCodPacchetto());
            list.remove(selected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkCode(Pacchetto selected, String msg) {
        try {
            if (LoginController.CodAgenzia.equals(selected.getCodAgenzia())) {
                return true;
            }
        } catch (NullPointerException e) {
            Utils.alertThrower("Non hai selezionato un pacchetto!");
        } catch (Exception e) {
            Utils.alertThrower("Non puoi " + msg + " un pacchetto non inserito da te!");
        }
        return false;
    }
}
