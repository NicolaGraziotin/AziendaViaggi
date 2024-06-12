package aziendaviaggi.controllers.client;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Pacchetto;
import aziendaviaggi.objects.Recensione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The controller class for the client selection view.
 * This class extends the base Controller class.
 */
public class ClientSelectionController extends Controller {

    @FXML
    private Pane ClientSelection;

    @FXML
    private DatePicker DataPartenza;

    @FXML
    private DatePicker DataRitorno;

    @FXML
    private TextField Email;

    @FXML
    private TextField Pacchetto;

    @FXML
    private ChoiceBox<String> Assicurazione;

    @FXML
    private ChoiceBox<String> Documento;

    @FXML
    private ChoiceBox<String> Metodo;

    @FXML
    private TextField PrezzoTotale;

    @FXML
    private TableView<Recensione> TableRecensioni;

    @FXML
    private TableColumn<Recensione, String> ColumnEmail;

    @FXML
    private TableColumn<Recensione, String> ColumnVoto;

    @FXML
    private TableColumn<Recensione, String> ColumnCommento;

    @FXML
    private TextArea Specifiche;

    private static Pacchetto actual;

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
        actual = ClientAppController.getActual();
        Email.setText(LoginController.getEmailCliente());
        Pacchetto.setText(actual.getCodPacchetto());
        choiceBoxInit("CodAssicurazione", "ASSICURAZIONI", Assicurazione);
        documentInit();
        metodoInit();

        cellInitRec(ColumnEmail, "Email");
        cellInitRec(ColumnVoto, "Voto");
        cellInitRec(ColumnCommento, "Commento");
        TableRecensioni.setItems(fillTableView());
    }

    /**
     * Handles the pay button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void pay(final ActionEvent event) {
        if (!checkInsert(ClientSelection)) {
            return;
        }
        try {
            final String codPrenotazione = progressiveCode("CodPrenotazione", "PRENOTAZIONI", "PR");
            this.statement.executeUpdate("INSERT INTO PRENOTAZIONI " + "VALUES ("
                    + valueFormatter(codPrenotazione) + ", "
                    + valueFormatter(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) + ", "
                    + valueFormatter(DataPartenza.getValue().toString()) + ", "
                    + valueFormatter(DataRitorno.getValue().toString()) + ", "
                    + valueFormatter(PrezzoTotale.getText()) + ", "
                    + valueFormatter(Email.getText()) + ", "
                    + valueFormatter(actual.getCodPacchetto()) + ", "
                    + valueFormatter(Assicurazione.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Documento.getSelectionModel().getSelectedItem()) + ", "
                    + chooseMethod("CC") + ", "
                    + chooseMethod("BB")
                    + ")");
            System.out.println("Prenotazione " + codPrenotazione + " aggiunta con successo!");
            changeScene(event, "ClientApp");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the insertDocumento button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void insertDocumento(final ActionEvent event) {
        changeScene(event, "ClientDocument");
    }

    /**
     * Handles the insertBonifico button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void insertBonifico(final ActionEvent event) {
        changeScene(event, "ClientBank");
    }

    /**
     * Handles the insertCarta button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void insertCarta(final ActionEvent event) {
        changeScene(event, "ClientCard");
    }

    /**
     * Handles the back button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "ClientApp");
    }

    /**
     * Handles the update button action event.
     *
     * @param event The action event.
     */
    @FXML
    private void updateAssicurazione(final ActionEvent event) {
        try {
            ResultSet res = this.statement
                    .executeQuery("SELECT Prezzo FROM ASSICURAZIONI WHERE CodAssicurazione = "
                            + valueFormatter(Assicurazione.getValue()));
            if (res.next()) {
                System.out.println(res.getString("Prezzo"));
                PrezzoTotale.setText(String
                        .valueOf(Float.parseFloat(actual.getPrezzo()) + Float.parseFloat(res.getString("Prezzo"))));
            }
            res = this.statement.executeQuery(
                    "SELECT * FROM ASSICURAZIONI WHERE CodAssicurazione = " + valueFormatter(Assicurazione.getValue()));
            if (res.next()) {
                print("Tipo: " + res.getString("Tipo")+ "\n"
                        + "Copertura: " + res.getString("Copertura") + "\n"
                        + "Prezzo: " + res.getString("Prezzo"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateDocumento(final ActionEvent event) {
        try {
            ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM DOCUMENTI_VIAGGIO WHERE NumeroDocumento = " + valueFormatter(Documento.getValue()));
            if (res.next()) {
                print("Luogo rilascio: " + res.getString("LuogoRilascio") + "\n"
                        + "Data scadenza" + res.getString("DataScadenza") + "\n"
                        + "Passaporto: " + res.getString("PASSAPORTO") + "\n"
                        + "Carta d'identita: " + res.getString("CARTA_IDENTITA"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateMetodo(final ActionEvent event) {
        try {
            if (Metodo.getValue().startsWith("CC")) {
                ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM CARTE_CREDITO WHERE CodCartaCredito = " + valueFormatter(Metodo.getValue()));
                if (res.next()) {
                    print("Intestatario: " + res.getString("Intestatario") + "\n"
                            + "Data scadenza: " + res.getString("DataScadenza") + "\n"
                            + "CVV: " + res.getString("CVV"));
                }
                
            } else {
                ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM BONIFICI_BANCARI WHERE CodBonifico = " + valueFormatter(Metodo.getValue()));
                if (res.next()) {
                    print("Nome ordinante: " + res.getString("NomeOrdinante") + "\n"
                            + "Conto ordinante: " + res.getString("ContoOrdinante") + "\n"
                            + "Nome beneficiario: " + res.getString("NomeBeneficiario") + "\n"
                            + "Conto beneficiario: " + res.getString("ContoBeneficiario") + "\n"
                            + "Causale: " + res.getString("Causale"));
                }
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print(String msg) {
        Specifiche.setText(msg);
    }

    /**
     * Initializes a table column for the Recensione class.
     *
     * @param cell  The table column to initialize.
     * @param value The value to set as the cell value factory.
     */
    protected void cellInitRec(final TableColumn<Recensione, String> cell, final String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Recensione, String>(value));
    }

    /**
     * Chooses the payment method based on the selected method.
     *
     * @param start The starting string of the selected method.
     * @return The formatted payment method or "NULL" if no method is selected.
     */
    private String chooseMethod(final String start) {
        final String selectedMethod = Metodo.getSelectionModel().getSelectedItem();
        if (selectedMethod.startsWith(start)) {
            return valueFormatter(selectedMethod);
        }
        return "NULL";
    }

    /**
     * Initializes the choice box with values from the specified column and table.
     *
     * @param column The column name.
     * @param table  The table name.
     * @param choice The choice box to initialize.
     */
    private void choiceBoxInit(final String column, final String table, final ChoiceBox<String> choice) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the document choice box with values based on the client's email.
     */
    private void documentInit() {
        try {
            System.out.println(LoginController.getEmailCliente());
            final ResultSet res = this.statement
                    .executeQuery("SELECT NumeroDocumento FROM DOCUMENTI_VIAGGIO WHERE Email = "
                            + valueFormatter(LoginController.getEmailCliente()));
            while (res.next()) {
                Documento.getItems().add(res.getString("NumeroDocumento"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the payment method choice box with values based on the client's
     * email.
     */
    private void metodoInit() {
        try {
            ResultSet res = this.statement.executeQuery("SELECT * FROM CARTE_CREDITO WHERE Email = "
                    + valueFormatter(LoginController.getEmailCliente()));
            while (res.next()) {
                Metodo.getItems().add(res.getString("CodCartaCredito"));
            }
            res = this.statement.executeQuery("SELECT * FROM BONIFICI_BANCARI WHERE Email = "
                    + valueFormatter(LoginController.getEmailCliente()));
            while (res.next()) {
                Metodo.getItems().add(res.getString("CodBonifico"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the table view with recensione data.
     *
     * @return The observable list of recensioni to populate the table view.
     */
    protected final ObservableList<Recensione> fillTableView() {
        ObservableList<Recensione> list = FXCollections.observableArrayList();
        try {
            final ResultSet res = this.statement.executeQuery("SELECT * FROM RECENSIONI WHERE CodPacchetto = "
                    + valueFormatter(actual.getCodPacchetto()));
            while (res.next()) {
                list.add(new Recensione(res.getString("CodPacchetto"), res.getString("Email"),
                        res.getString("Voto"), res.getString("Commento")));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
