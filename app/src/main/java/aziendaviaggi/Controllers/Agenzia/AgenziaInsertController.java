package aziendaviaggi.controllers.agenzia;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Attivita;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * Controller class for the AgenziaInsert.fxml file.
 */
public class AgenziaInsertController extends Controller {

    @FXML
    private Pane AgenziaInsert;

    @FXML
    private TextField Descrizione;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Prezzo;

    @FXML
    private ChoiceBox<String> Alloggio;

    @FXML
    private ChoiceBox<String> Destinazione;

    @FXML
    private ChoiceBox<String> Guida;

    @FXML
    private ChoiceBox<String> Trasporto;

    @FXML
    private TextField Agenzia;

    @FXML
    private TableView<Attivita> TableAttivita;

    @FXML
    private TableColumn<Attivita, String> ColumnNome;

    @FXML
    private TableColumn<Attivita, String> ColumnDescrizione;

    @FXML
    private TableColumn<Attivita, String> ColumnOrario;

    @FXML
    private TableColumn<Attivita, String> ColumnDurata;

    @FXML
    private TextArea Specifiche;

    /**
     * Initializes the controller.
     * 
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Agenzia.setText(LoginController.getCodAgenzia());
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);

        cellInit(ColumnNome, "Nome");
        cellInit(ColumnDescrizione, "Descrizione");
        cellInit(ColumnOrario, "Orario");
        cellInit(ColumnDurata, "Durata");

        TableAttivita.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableAttivita.setItems(fillTableView());
    }

    /**
     * Handles the enter button action.
     * 
     * @param event The action event.
     */
    @FXML
    private void enter(final ActionEvent event) {
        var AgenziaCheck = FXCollections.observableArrayList(AgenziaInsert.getChildren());
        AgenziaCheck.remove(Specifiche);
        if (!checkInsert(AgenziaCheck)) {
            return;
        }
        if (!Prezzo.getText().matches("^\\d+$")) {
            alertThrower("Prezzo deve essere una valore numerico.");
            return;
        }
        executeTryBlock(() -> {
            final String codPacchetto = progressiveCode("CodPacchetto", "PACCHETTI_TURISTICI", "PT");
            this.statement.executeUpdate("INSERT INTO PACCHETTI_TURISTICI VALUES ("
                    + valueFormatter(codPacchetto) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Descrizione.getText()) + ", "
                    + valueFormatter(Prezzo.getText()) + ", "
                    + valueFormatter(LoginController.getCodAgenzia()) + ", "
                    + guidaCheck(Guida.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Trasporto.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Alloggio.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Destinazione.getSelectionModel().getSelectedItem())
                    + ")");
            System.out.println("Pacchetto: " + codPacchetto + " aggiunto.");
            final ObservableList<Attivita> selectedItems = TableAttivita.getSelectionModel().getSelectedItems();
            for (final Attivita attivita : selectedItems) {
                this.statement.executeUpdate("INSERT INTO ITINERARI VALUES ("
                        + valueFormatter(codPacchetto) + ", "
                        + valueFormatter(attivita.getCodAttivita())
                        + ")");
                System.out.println(
                        "Attivita: " + attivita.getCodAttivita() + " aggiunta al pacchetto " + codPacchetto + ".");
            }
            back(event);
        });
    }

    /**
     * Prints the details of a tour guide based on the provided guide code.
     * 
     * @param event The mouse event that triggered the method.
     */
    @FXML
    protected final void printGuida(final ActionEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM GUIDE_TURISTICHE WHERE CodGuida = " + valueFormatter(Guida.getValue()));
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
    protected final void printTrasporto(final ActionEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM TRASPORTI WHERE CodTrasporto = " + valueFormatter(Trasporto.getValue()));
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
     * Prints information about an accommodation based on the provided CodAlloggio value.
     * 
     * @param event The MouseEvent that triggered the method.
     */
    @FXML
    protected final void printAlloggio(final ActionEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM ALLOGGI WHERE CodAlloggio = " + valueFormatter(Alloggio.getValue()));
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
     * Prints the details of a destination based on the provided CodDestinazione value.
     * 
     * @param event The MouseEvent that triggered the method.
     */
    @FXML
    protected final void printDestinazione(final ActionEvent event) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM DESTINAZIONI WHERE CodDestinazione = " + valueFormatter(Destinazione.getValue()));
            if (res.next()) {
                print("Paese: " + res.getString("Paese") + "\n"
                        + "Citta: " + res.getString("Citta") + "\n"
                        + "Descrizione: " + res.getString("Descrizione"));
            }
        });
    }

    /**
     * Handles the back button action.
     * 
     * @param event The action event.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "AgenziaApp");
    }

    /**
     * Prints the given message to the Specifiche text field.
     *
     * @param msg the message to be printed
     */
    private final void print(String msg) {
        Specifiche.setText(msg);
    }

    /**
     * Fills the TableView with data from the database.
     * 
     * @return The ObservableList of Attivita objects.
     */
    private ObservableList<Attivita> fillTableView() {
        final ObservableList<Attivita> list = FXCollections.observableArrayList();
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM ATTIVITA");
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"), res.getString("Descrizione"),
                        res.getString("Orario"), res.getString("Durata")));
            }
        });
        return list;
    }

    /**
     * Initializes a TableColumn with a specific value.
     * 
     * @param cell  The TableColumn to initialize.
     * @param value The value to set for the TableColumn.
     */
    private void cellInit(final TableColumn<Attivita, String> cell, final String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Attivita, String>(value));
    }

    /**
     * Checks if the selected guide is "NULL" and returns the appropriate value.
     * 
     * @param guida The selected guide.
     * @return The formatted value of the guide.
     */
    protected String guidaCheck(final String guida) {
        return guida.equals("NULL") ? guida : valueFormatter(guida);
    }

    /**
     * Initializes a ChoiceBox with values from a specific column in a table.
     * 
     * @param column The column to retrieve values from.
     * @param table  The table to retrieve values from.
     * @param choice The ChoiceBox to initialize.
     */
    protected void choiceBoxInit(final String column, final String table, final ChoiceBox<String> choice) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        });
    }
}
