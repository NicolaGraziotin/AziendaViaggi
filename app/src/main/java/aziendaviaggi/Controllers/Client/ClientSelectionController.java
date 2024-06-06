package aziendaviaggi.controllers.client;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    private static Pacchetto actual;

    /**
     * Initializes the controller.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        actual = ClientAppController.getActual();
        Email.setText(LoginController.getEmailCliente());
        Pacchetto.setText(actual.getCodPacchetto());
        choiceBoxInit("CodAssicurazione", "ASSICURAZIONI", Assicurazione);
        documentInit();
        metodoInit();
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
    private void update(final ActionEvent event) {
        try {
            final ResultSet res = this.statement.executeQuery("SELECT Prezzo FROM ASSICURAZIONI WHERE CodAssicurazione = "
                    + valueFormatter(Assicurazione.getValue()));
            if (res.next()) {
                System.out.println(res.getString("Prezzo"));
                PrezzoTotale.setText(String
                        .valueOf(Float.parseFloat(actual.getPrezzo()) + Float.parseFloat(res.getString("Prezzo"))));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            final ResultSet res = this.statement.executeQuery("SELECT NumeroDocumento FROM DOCUMENTI_VIAGGIO WHERE Email = "
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
     * Initializes the payment method choice box with values based on the client's email.
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
}
