package aziendaviaggi.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.Objects.Pacchetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClientSelectionController extends Controller {

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
    }
    
    @FXML
    private void pay(ActionEvent event) {

    }

    @FXML
    private void insertDocument(ActionEvent event) {

    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "clientApp");
    }
}
