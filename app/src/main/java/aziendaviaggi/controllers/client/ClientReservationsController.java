package aziendaviaggi.controllers.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Pacchetto;

public class ClientReservationsController extends ClientAppController {

    @FXML
    private Pane ClientReservations;

    @FXML
    private TextField DataPrenotazione;

    @FXML
    private TextField DataPartenza;

    @FXML
    private TextField DataRitorno;

    @FXML
    private TextField Assicurazione;

    @FXML
    private TextField Documento;

    @FXML
    private TextField Metodo;

    @FXML
    private TextField Prezzo;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        cellInit(ColumnAgenzia, "CodAgenzia");
        cellInit(ColumnNome, "Nome");
        cellInit(ColumnDescrizione, "Descrizione");
        cellInit(ColumnPrezzo, "Prezzo");

        cellInit(ColumnNomeAttivita, "Nome");
        cellInit(ColumnDescrizioneAttivita, "Descrizione");
        cellInit(ColumnOrario, "Orario");
        cellInit(ColumnDurata, "Durata");

        TablePacchetti.setItems(fillTableView());
    }

    /**
     * Handles the back button action event.
     *
     * @param event The action event.
     */
    @FXML
    protected void back(final ActionEvent event) {
        changeScene(event, "ClientApp");
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

    @FXML
    private void printAssicurazione(final MouseEvent event) {
        super.printAssicurazione(Assicurazione.getText(), Specifiche);
    }

    @FXML
    private void printDocumento(final MouseEvent event) {
        super.printDocumento(Documento.getText(), Specifiche);
    }

    @FXML
    private void printMetodo(final MouseEvent event) {
        super.printMetodo(Metodo.getText(), Specifiche);
    }

    /**
     * Handles the table view row selection event.
     * It updates the text fields with the selected pacchetto's data and populates
     * the attivita table view.
     *
     * @param event The mouse event triggered by the table view row selection.
     */
    @FXML
    protected void update(final MouseEvent event) {
        super.update(event);
        selectedPacchetto = TablePacchetti.getSelectionModel().getSelectedItem();
        if (selectedPacchetto == null) {
            return;
        }
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM PRENOTAZIONI WHERE CodPacchetto = "
                            + valueFormatter(selectedPacchetto.getCodPacchetto()));
            if (res.next()) {
                DataPrenotazione.setText(res.getString("DataPrenotazione"));
                DataPartenza.setText(res.getString("DataPartenza"));
                DataRitorno.setText(res.getString("DataRitorno"));
                Assicurazione.setText(res.getString("CodAssicurazione"));
                Documento.setText(res.getString("NumeroDocumento"));
                Metodo.setText(res.getString("CodCartaCredito") != null ? res.getString("CodCartaCredito")
                        : res.getString("CodBonifico"));
                Prezzo.setText(res.getString("Importo"));
            }
        });
    }

    /**
     * Returns the currently selected pacchetto.
     *
     * @return the selected pacchetto
     */
    public static Pacchetto getActual() {
        return selectedPacchetto;
    }

    /**
     * Retrieves a list of reservations for a client and populates a TableView with the data.
     *
     * @return The ObservableList of Pacchetto objects representing the reservations.
     */
    protected final ObservableList<Pacchetto> fillTableView() {
        ObservableList<Pacchetto> list = FXCollections.observableArrayList();
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM PRENOTAZIONI P RIGHT JOIN PACCHETTI_TURISTICI T ON (P.CodPacchetto = T.CodPacchetto) WHERE Email = "
                            + valueFormatter(LoginController.getEmailCliente()));
            while (res.next()) {
                list.add(new Pacchetto(res.getString("CodPacchetto"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Prezzo"), res.getString("CodAgenzia"),
                        res.getString("CodGuida"), res.getString("CodTrasporto"), res.getString("CodAlloggio"),
                        res.getString("CodDestinazione")));
            }
        });
        return list;
    }
}
