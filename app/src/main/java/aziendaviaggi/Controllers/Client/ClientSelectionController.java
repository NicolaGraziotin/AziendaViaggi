package aziendaviaggi.Controllers.Client;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;
import aziendaviaggi.Objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actual = ClientAppController.getActual();
        Email.setText(LoginController.getEmailCliente());
        Pacchetto.setText(actual.getCodPacchetto());
        choiceBoxInit("CodAssicurazione", "ASSICURAZIONI", Assicurazione);
        documentInit();
        metodoInit();
    }

    @FXML
    private void pay(ActionEvent event) {
        if (!checkInsert(ClientSelection)) {
            return;
        }
        try {
            String codPrenotazione = progressiveCode("CodPrenotazione", "PRENOTAZIONI", "PR");
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

    @FXML
    private void insertDocumento(ActionEvent event) {
        changeScene(event, "ClientDocument");
    }

    @FXML
    private void insertBonifico(ActionEvent event) {
        changeScene(event, "ClientBank");
    }

    @FXML
    private void insertCarta(ActionEvent event) {
        changeScene(event, "ClientCard");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "ClientApp");
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT Prezzo FROM ASSICURAZIONI WHERE CodAssicurazione = "
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

    private String chooseMethod(String start) {
        String selectedMethod = Metodo.getSelectionModel().getSelectedItem();
        if (selectedMethod.startsWith(start))
            return valueFormatter(selectedMethod);
        return "NULL";
    }

    private void choiceBoxInit(String column, String table, ChoiceBox<String> choice) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void documentInit() {
        try {
            System.out.println(LoginController.getEmailCliente());
            ResultSet res = this.statement.executeQuery("SELECT NumeroDocumento FROM DOCUMENTI_VIAGGIO WHERE Email = "
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
