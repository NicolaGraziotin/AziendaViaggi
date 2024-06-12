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
import java.sql.SQLException;

/**
 * The ControllerApp class is responsible for controlling the main application view.
 * It handles the initialization of the view, event handling, and data retrieval.
 */
public class ControllerApp extends Controller {

    /**
     * The table view for the pacchetti.
     */
    @FXML
    protected TableView<Pacchetto> TablePacchetti;

    /**
     * The table columns for the pacchetti.
     */
    @FXML
    protected TableColumn<Pacchetto, String> ColumnAgenzia;

    /**
     * The table columns for the pacchetti.
     */
    @FXML
    protected TableColumn<Pacchetto, String> ColumnDescrizione;

    /**
     * The table columns for the pacchetti.
     */
    @FXML
    protected TableColumn<Pacchetto, String> ColumnNome;

    /**
     * The table columns for the pacchetti.
     */
    @FXML
    protected TableColumn<Pacchetto, String> ColumnPrezzo;

    /**
     * The text field for the guida.
     */
    @FXML
    protected TextField Guida;

    /**
     * The text field for the trasporto.
     */
    @FXML
    protected TextField Trasporto;

    /**
     * The text field for the alloggio.
     */
    @FXML
    protected TextField Alloggio;

    /**
     * The text field for the destinazione.
     */
    @FXML
    protected TextField Destinazione;

    /**
     * The table view for the attivita.
     */
    @FXML
    protected TableView<Attivita> TableAttivita;

    /**
     * The table columns for the attivita.
     */
    @FXML
    protected TableColumn<Attivita, String> ColumnNomeAttivita;

    /**
     * The table columns for the attivita.
     */
    @FXML
    protected TableColumn<Attivita, String> ColumnDescrizioneAttivita;

    /**
     * The table columns for the attivita.
     */
    @FXML
    protected TableColumn<Attivita, String> ColumnOrario;

    /**
     * The table columns for the attivita.
     */
    @FXML
    protected TableColumn<Attivita, String> ColumnDurata;

    @FXML
    protected TextArea Specifiche;

    /**
     * Initializes the ControllerApp view.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the table columns and populates the table view with data.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
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
    protected final void back(final ActionEvent event) {
        changeScene(event, "Login");
    }

    /**
     * Handles the table view row selection event.
     * It updates the text fields with the selected pacchetto's data and populates the attivita table view.
     *
     * @param event The mouse event triggered by the table view row selection.
     */
    @FXML
    protected final void update(final MouseEvent event) {
        Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
        Guida.setText(sel.getCodGuida());
        Trasporto.setText(sel.getCodTrasporto());
        Alloggio.setText(sel.getCodAlloggio());
        Destinazione.setText(sel.getCodDestinazione());
        TableAttivita.setItems(fillAttivita());
    }

    @FXML
    protected final void printGuida(final MouseEvent event) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM GUIDE_TURISTICHE WHERE CodGuida = " + valueFormatter(Guida.getText()));
            if (res.next()) {
                print("Nome: " + res.getString("Nome") + "\n"
                        + "Cognome: " + res.getString("Cognome") + "\n"
                        + "Lingua: " + res.getString("Lingua") + "\n"
                        + "Esperienza: " + res.getString("Esperienza"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected final void printTrasporto(final MouseEvent event) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM TRASPORTI WHERE CodTrasporto = " + valueFormatter(Trasporto.getText()));
            if (res.next()) {
                print("Compagnia: " + res.getString("Compagnia") + "\n"
                        + "Partenza: " + res.getString("Partenza") + "\n"
                        + "Destinazione: " + res.getString("Destinazione") + "\n"
                        + "Orario: " + res.getString("Orario") + "\n"
                        + "Traghetto: " + res.getString("TRAGHETTO") + "\n"
                        + "Autobus: " + res.getString("AUTOBUS") + "\n"
                        + "Aereo: " + res.getString("AEREO"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected final void printAlloggio(final MouseEvent event) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM ALLOGGI WHERE CodAlloggio = " + valueFormatter(Alloggio.getText()));
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
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected final void printDestinazione(final MouseEvent event) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM DESTINAZIONI WHERE CodDestinazione = " + valueFormatter(Destinazione.getText()));
            if (res.next()) {
                print("Paese: " + res.getString("Paese") + "\n"
                        + "Citta: " + res.getString("Citta") + "\n"
                        + "Descrizione: " + res.getString("Descrizione"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void print(String msg) {
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
    protected final ObservableList<Pacchetto> fillTableView() {
        ObservableList<Pacchetto> list = FXCollections.observableArrayList();
        try {
            ResultSet res = this.statement.executeQuery("SELECT * FROM PACCHETTI_TURISTICI");
            while (res.next()) {
                list.add(new Pacchetto(res.getString("CodPacchetto"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Prezzo"), res.getString("CodAgenzia"),
                        res.getString("CodGuida"), res.getString("CodTrasporto"), res.getString("CodAlloggio"),
                        res.getString("CodDestinazione")));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Fills the attivita table view with data based on the selected pacchetto.
     *
     * @return The observable list of attivita to populate the table view.
     */
    private ObservableList<Attivita> fillAttivita() {
        ObservableList<Attivita> list = FXCollections.observableArrayList();
        try {
            Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
            ResultSet res = this.statement.executeQuery(
                    "SELECT A.* "
                    + "FROM ITINERARI I RIGHT JOIN ATTIVITA A ON(I.CodAttivita = A.CodAttivita) "
                    + "WHERE I.CodPacchetto = " + valueFormatter(sel.getCodPacchetto()));
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Orario"),
                        res.getString("Durata")));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
