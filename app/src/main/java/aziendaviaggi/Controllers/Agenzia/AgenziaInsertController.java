package aziendaviaggi.controllers.agenzia;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Attivita;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        if (!checkInsert(AgenziaInsert)) {
            return;
        }
        try {
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
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
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
     * Fills the TableView with data from the database.
     * 
     * @return The ObservableList of Attivita objects.
     */
    private ObservableList<Attivita> fillTableView() {
        final ObservableList<Attivita> list = FXCollections.observableArrayList();
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM ATTIVITA");
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"), res.getString("Descrizione"),
                        res.getString("Orario"), res.getString("Durata")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            final ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
