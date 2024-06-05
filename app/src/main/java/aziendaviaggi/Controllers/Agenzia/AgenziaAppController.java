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

    private static Pacchetto selectedPacchetto;

    @FXML
    private void insert(ActionEvent event) {
        changeScene(event, "AgenziaInsert");
    }

    @FXML
    private void delete(ActionEvent event) {
        Pacchetto selected = TableV.getSelectionModel().getSelectedItem();
        if (checkSelected(selected, "eliminare") && Utils.confirmThrower("Sei sicuro di volere eliminare il pacchetto?"))
            remove(selected);
    }

    @FXML
    private void modify(ActionEvent event) {
        selectedPacchetto = TableV.getSelectionModel().getSelectedItem();
        if (checkSelected(selectedPacchetto, "modificare"))
            changeScene(event, "AgenziaModify");
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

    private boolean checkSelected(Pacchetto selected, String msg) {
        try {
            if (LoginController.getCodAgenzia().equals(selected.getCodAgenzia())) {
                return true;
            }
        } catch (NullPointerException e) {
            alertThrower("Non hai selezionato un pacchetto!");
        } catch (Exception e) {
            alertThrower("Non puoi " + msg + " un pacchetto non inserito da te!");
        }
        return false;
    }

    public static Pacchetto getSelectedPacchetto() {
        return selectedPacchetto;
    }
}
