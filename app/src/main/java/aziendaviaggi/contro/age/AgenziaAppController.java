package aziendaviaggi.contro.age;

import aziendaviaggi.Utils;
import aziendaviaggi.contro.ControllerApp;
import aziendaviaggi.contro.LoginController;
import aziendaviaggi.obje.Pacchetto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * The AgenziaAppController class is responsible for handling the actions and events related to the Agenzia App UI.
 * It extends the ControllerApp class and provides methods for inserting, deleting, modifying, and retrieving Pacchetto objects.
 */
public class AgenziaAppController extends ControllerApp {

    @FXML
    private Pane AgenziaApp;

    private static Pacchetto selectedPacchetto;

    /**
     * Handles the insert action event.
     * Changes the scene to the AgenziaInsert view.
     *
     * @param event The action event triggered by the insert button.
     */
    @FXML
    private void insert(final ActionEvent event) {
        changeScene(event, "AgenziaInsert");
    }

    /**
     * Handles the delete action event.
     * Removes the selected Pacchetto object from the table if confirmed by the user.
     *
     * @param event The action event triggered by the delete button.
     */
    @FXML
    private void delete(final ActionEvent event) {
        selectedPacchetto = TablePacchetti.getSelectionModel().getSelectedItem();
        if (checkSelected(selectedPacchetto, "eliminare") && Utils.confirmThrower("Sei sicuro di volere eliminare il pacchetto?")) {
            remove(selectedPacchetto);
            Alloggio.setText(null);
            Destinazione.setText(null);
            Guida.setText(null);
            Trasporto.setText(null);
            Specifiche.setText(null);
            TableAttivita.getItems().clear();
        }
    }

    /**
     * Handles the modify action event.
     * Sets the selected Pacchetto object and changes the scene to the AgenziaModify view.
     *
     * @param event The action event triggered by the modify button.
     */
    @FXML
    private void modify(final ActionEvent event) {
        selectedPacchetto = TablePacchetti.getSelectionModel().getSelectedItem();
        if (checkSelected(selectedPacchetto, "modificare")) {
            changeScene(event, "AgenziaModify");
        }
    }

    /**
     * Removes the selected Pacchetto object from the table and the database.
     *
     * @param selected The Pacchetto object to be removed.
     */
    private void remove(final Pacchetto selected) {
        final ObservableList<Pacchetto> list = TablePacchetti.getItems();
        executeTryBlock(() -> {
            this.statement.executeUpdate(
                    "DELETE FROM PACCHETTI_TURISTICI WHERE CodPacchetto=" + valueFormatter(selected.getCodPacchetto()));
            list.remove(selected);
        });
    }

    /**
     * Checks if the selected Pacchetto object is valid for the specified action.
     *
     * @param selected The selected Pacchetto object.
     * @param msg      The message to be displayed in case of an invalid selection.
     * @return True if the selected Pacchetto object is valid, false otherwise.
     */
    private boolean checkSelected(final Pacchetto selected, final String msg) {
        try {
            if (LoginController.getCodAgenzia().equals(selected.getCodAgenzia())) {
                return true;
            } else {
                alertThrower("Non puoi " + msg + " un pacchetto non inserito da te!");
            }
        } catch (NullPointerException e) {
            alertThrower("Non hai selezionato un pacchetto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the selected Pacchetto object.
     *
     * @return The selected Pacchetto object.
     */
    public static Pacchetto getSelectedPacchetto() {
        return selectedPacchetto;
    }
}
