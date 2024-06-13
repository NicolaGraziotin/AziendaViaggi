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
        cellInitPac(ColumnAgenzia, "CodAgenzia");
        cellInitPac(ColumnNome, "Nome");
        cellInitPac(ColumnDescrizione, "Descrizione");
        cellInitPac(ColumnPrezzo, "Prezzo");

        cellInitAtt(ColumnNomeAttivita, "Nome");
        cellInitAtt(ColumnDescrizioneAttivita, "Descrizione");
        cellInitAtt(ColumnOrario, "Orario");
        cellInitAtt(ColumnDurata, "Durata");

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

    /**
     * Handles the update button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void printAssicurazione(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM ASSICURAZIONI WHERE CodAssicurazione = " + valueFormatter(Assicurazione.getText()));
            if (res.next()) {
                print("Tipo: " + res.getString("Tipo") + "\n"
                        + "Copertura: " + res.getString("Copertura") + "\n"
                        + "Prezzo: " + res.getString("Prezzo"));
            }
        });
    }

    /**
     * Updates the document based on the selected value.
     *
     * @param event The event that triggered the update.
     */
    @FXML
    private void printDocumento(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM DOCUMENTI_VIAGGIO WHERE NumeroDocumento = " + valueFormatter(Documento.getText()));
            if (res.next()) {
                print("Luogo rilascio: " + res.getString("LuogoRilascio") + "\n"
                        + "Data scadenza: " + res.getString("DataScadenza") + "\n"
                        + "Passaporto: " + res.getString("PASSAPORTO") + "\n"
                        + "Carta d'identita: " + res.getString("CARTA_IDENTITA"));
            }
        });
    }

    /**
     * Updates the method based on the selected value.
     * If the selected value starts with "CC", it retrieves information from the
     * "CARTE_CREDITO" table.
     * If the selected value does not start with "CC", it retrieves information from
     * the "BONIFICI_BANCARI" table.
     *
     * @param event the action event that triggered the update
     */
    @FXML
    private void printMetodo(final MouseEvent event) {
        executeTryBlock(() -> {
            if (Metodo.getText().startsWith("CC")) {
                final ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM CARTE_CREDITO WHERE CodCartaCredito = " + valueFormatter(Metodo.getText()));
                if (res.next()) {
                    print("Intestatario: " + res.getString("Intestatario") + "\n"
                            + "Numero carta: " + res.getString("Numero") + "\n"
                            + "Data scadenza: " + res.getString("DataScadenza") + "\n"
                            + "CVV: " + res.getString("CVV"));
                }
            } else {
                final ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM BONIFICI_BANCARI WHERE CodBonifico = " + valueFormatter(Metodo.getText()));
                if (res.next()) {
                    print("Nome ordinante: " + res.getString("NomeOrdinante") + "\n"
                            + "Conto ordinante: " + res.getString("ContoOrdinante") + "\n"
                            + "Nome beneficiario: " + res.getString("NomeBeneficiario") + "\n"
                            + "Conto beneficiario: " + res.getString("ContoBeneficiario") + "\n"
                            + "Causale: " + res.getString("Causale"));
                }
            }
        });
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
