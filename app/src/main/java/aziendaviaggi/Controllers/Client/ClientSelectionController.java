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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientSelectionController extends Controller {

    @FXML
    private Pane ClientSelect;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Descrizione;

    @FXML
    private TextField Prezzo;

    @FXML
    private TextField Agenzia;

    @FXML
    private TextField Trasporto;

    @FXML
    private TextField Guida;

    @FXML
    private TextField Alloggio;

    @FXML
    private TextField Destinazione;

    @FXML
    private TextField PrezzoTotale;

    @FXML
    private ChoiceBox<String> Assicurazione;

    @FXML
    private ChoiceBox<String> Documento;

    @FXML
    private ChoiceBox<String> Metodo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pacchetto actual = ClientAppController.getActual();
        Nome.setText(actual.getNome());
        Descrizione.setText(actual.getDescrizione());
        Prezzo.setText(actual.getPrezzo());
        Agenzia.setText(actual.getCodAgenzia());
        Trasporto.setText(actual.getCodTrasporto());
        Guida.setText(actual.getCodGuida());
        Alloggio.setText(actual.getCodAlloggio());
        Destinazione.setText(actual.getCodDestinazione());
        choiceBoxInit("CodAssicurazione", "ASSICURAZIONI", Assicurazione);
        documentInit();
        Metodo.getItems().addAll("Carta di Credito", "Bonifico Bancario");
    }
    
    @FXML
    private void pay(ActionEvent event) {
        if (Metodo.getSelectionModel().getSelectedItem().equals("Carta di Credito")) {
            changeScene(event, "clientPayCard");
        } else {
            changeScene(event, "clientPayBank");
        }
    }

    @FXML
    private void insertDocument(ActionEvent event) {
        changeScene(event, "clientDocument");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "clientApp");
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT Prezzo FROM ASSICURAZIONI WHERE CodAssicurazione = "
                    + valueFormatter(Assicurazione.getValue()));
            if (res.next()) {
                System.out.println(res.getString("Prezzo"));
                PrezzoTotale.setText(String.valueOf(Float.parseFloat(Prezzo.getText()) + Float.parseFloat(res.getString("Prezzo"))));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addMethod(ActionEvent event) {
        changeScene(event, "clientMethod");
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
            ResultSet res = this.statement.executeQuery("SELECT NumeroDocumento FROM DOCUMENTI_VIAGGIO WHERE Email = " + valueFormatter(LoginController.EmailCliente));
            while (res.next()) {
                Documento.getItems().add(res.getString("NumeroDocumento"));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
