package aziendaviaggi.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import aziendaviaggi.objects.Attivita;
import aziendaviaggi.objects.Pacchetto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * The ControllerApp class is responsible for controlling the main application
 * view.
 * It handles the initialization of the view, event handling, and data
 * retrieval.
 */
public class ControllerApp extends Controller {

    @FXML
    protected TableView<Pacchetto> TablePacchetti;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnAgenzia;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnDescrizione;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnNome;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnPrezzo;

    @FXML
    protected TextField Guida;

    @FXML
    protected TextField Trasporto;

    @FXML
    protected TextField Alloggio;

    @FXML
    protected TextField Destinazione;

    @FXML
    protected TableView<Attivita> TableAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnNomeAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnDescrizioneAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnOrario;

    @FXML
    protected TableColumn<Attivita, String> ColumnDurata;

    @FXML
    protected TextArea Specifiche;

    /**
     * Initializes the ControllerApp view.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the table columns and populates the table view with data.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the root object was not localized.
     */
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
     * Handles the back button click event.
     * It changes the scene to the login view.
     *
     * @param event The action event triggered by the back button click.
     */
    @FXML
    protected void back(final ActionEvent event) {
        changeScene(event, "Login");
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
        final Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
        Guida.setText(sel.getCodGuida());
        Trasporto.setText(sel.getCodTrasporto());
        Alloggio.setText(sel.getCodAlloggio());
        Destinazione.setText(sel.getCodDestinazione());
        TableAttivita.setItems(fillAttivita());
    }

    /**
     * Prints the details of a tour guide based on the provided guide code.
     * 
     * @param event The mouse event that triggered the method.
     */
    @FXML
    protected void printGuida(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement
                    .executeQuery("SELECT * FROM GUIDE_TURISTICHE WHERE CodGuida = " + valueFormatter(Guida.getText()));
            if (res.next()) {
                print("Nome: " + res.getString("Nome") + "\n"
                        + "Cognome: " + res.getString("Cognome") + "\n"
                        + "Lingua: " + res.getString("Lingua") + "\n"
                        + "Esperienza: " + res.getString("Esperienza"));
            }
        });
    }

    /**
     * Prints the details of a transportation record based on the given input.
     *
     * @param event The mouse event that triggered the method.
     */
    @FXML
    protected void printTrasporto(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM TRASPORTI WHERE CodTrasporto = " + valueFormatter(Trasporto.getText()));
            if (res.next()) {
                print("Compagnia: " + res.getString("Compagnia") + "\n"
                        + "Partenza: " + res.getString("Partenza") + "\n"
                        + "Destinazione: " + res.getString("Destinazione") + "\n"
                        + "Orario: " + res.getString("Orario") + "\n"
                        + "Traghetto: " + res.getString("TRAGHETTO") + "\n"
                        + "Autobus: " + res.getString("AUTOBUS") + "\n"
                        + "Aereo: " + res.getString("AEREO"));
            }
        });
    }

    /**
     * Prints information about an accommodation based on the provided CodAlloggio
     * value.
     * 
     * @param event The MouseEvent that triggered the method.
     */
    @FXML
    protected void printAlloggio(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement
                    .executeQuery("SELECT * FROM ALLOGGI WHERE CodAlloggio = " + valueFormatter(Alloggio.getText()));
            if (res.next()) {
                print("Nome: " + res.getString("Nome") + "\n"
                        + "Citta: " + res.getString("Ind_Citta") + "\n"
                        + "Via: " + res.getString("Ind_Via") + "\n"
                        + "Numero civico: " + res.getString("Ind_NumeroCivico") + "\n"
                        + "Numero camere: " + res.getString("NumeroCamere") + "\n"
                        + "Hotel: " + res.getString("HOTEL") + "\n"
                        + "Formula: " + res.getString("Formula") + "\n"
                        + "Appartamento: " + res.getString("APPARTAMENTO"));
            }
        });
    }

    /**
     * Prints the details of a destination based on the provided CodDestinazione
     * value.
     * 
     * @param event The MouseEvent that triggered the method.
     */
    @FXML
    protected void printDestinazione(final MouseEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM DESTINAZIONI WHERE CodDestinazione = " + valueFormatter(Destinazione.getText()));
            if (res.next()) {
                print("Paese: " + res.getString("Paese") + "\n"
                        + "Citta: " + res.getString("Citta") + "\n"
                        + "Descrizione: " + res.getString("Descrizione"));
            }
        });
    }

    /**
     * Prints the given message to the Specifiche text field.
     *
     * @param msg the message to be printed
     */
    protected void print(String msg) {
        Specifiche.setText(msg);
    }

    /**
     * Initializes a table column for the Pacchetto class.
     *
     * @param cell  The table column to initialize.
     * @param value The value to set as the cell value factory.
     */
    protected void cellInitPac(final TableColumn<Pacchetto, String> cell, final String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto, String>(value));
    }

    /**
     * Initializes a table column for the Attivita class.
     *
     * @param cell  The table column to initialize.
     * @param value The value to set as the cell value factory.
     */
    protected void cellInitAtt(final TableColumn<Attivita, String> cell, final String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Attivita, String>(value));
    }

    /**
     * Fills the table view with pacchetto data.
     *
     * @return The observable list of pacchetti to populate the table view.
     */
    protected ObservableList<Pacchetto> fillTableView() {
        ObservableList<Pacchetto> list = FXCollections.observableArrayList();
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM PACCHETTI_TURISTICI");
            while (res.next()) {
                list.add(new Pacchetto(res.getString("CodPacchetto"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Prezzo"), res.getString("CodAgenzia"),
                        res.getString("CodGuida"), res.getString("CodTrasporto"), res.getString("CodAlloggio"),
                        res.getString("CodDestinazione")));
            }
        });
        return list;
    }

    /**
     * Fills the attivita table view with data based on the selected pacchetto.
     *
     * @return The observable list of attivita to populate the table view.
     */
    private ObservableList<Attivita> fillAttivita() {
        final ObservableList<Attivita> list = FXCollections.observableArrayList();
        executeTryBlock(() -> {
            final Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
            final ResultSet res = this.statement.executeQuery(
                    "SELECT A.* "
                            + "FROM ITINERARI I RIGHT JOIN ATTIVITA A ON(I.CodAttivita = A.CodAttivita) "
                            + "WHERE I.CodPacchetto = " + valueFormatter(sel.getCodPacchetto()));
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Orario"),
                        res.getString("Durata")));
            }
        });
        return list;
    }
}
