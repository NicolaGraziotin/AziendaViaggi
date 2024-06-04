package aziendaviaggi.Controllers.Client;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

import aziendaviaggi.Utils;
import aziendaviaggi.Controllers.Controller;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pacchetto actual = ClientAppController.actual;
        Nome.setText(actual.getNome());
        Descrizione.setText(actual.getDescrizione());
        Prezzo.setText(actual.getPrezzo());
        Agenzia.setText(actual.getCodAgenzia());
        Trasporto.setText(actual.getCodTrasporto());
        Guida.setText(actual.getCodGuida());
        Alloggio.setText(actual.getCodAlloggio());
        Destinazione.setText(actual.getCodDestinazione());
        choiceBoxInit("CodAssicurazione", "ASSICURAZIONI", Assicurazione);
        choiceBoxInit("NumeroDocumento", "DOCUMENTI_VIAGGIO", Documento);
    }
    
    @FXML
    private void pay(ActionEvent event) {
        if (Assicurazione.getValue() == null || Documento.getValue() == null) {
            Utils.alertThrower("Assicurazione e Documento sono campi obbligatori");
            return;
        }
        changeScene(event, "clientSummary");
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
    private void updatePrice(ActionEvent event) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT Prezzo FROM ASSICURAZIONI WHERE CodAssicurazione = "
                    + Assicurazione.getValue());
            if (res.next()) {
                System.out.println(res.getString("Prezzo"));
                PrezzoTotale.setText(String.valueOf(Float.parseFloat(Prezzo.getText()) + Float.parseFloat(res.getString("Prezzo"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void choiceBoxInit(String column, String table, ChoiceBox<String> choice) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
