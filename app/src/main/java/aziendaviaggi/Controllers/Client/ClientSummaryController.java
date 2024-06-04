package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientSummaryController extends Controller {

    @FXML
    private TextField Agenzia;

    @FXML
    private TextField Alloggio;

    @FXML
    private TextField Assicurazione;

    @FXML
    private Pane ClientSummary;

    @FXML
    private TextField Descrizione;

    @FXML
    private TextField Destinazione;

    @FXML
    private TextField Documento;

    @FXML
    private TextField Guida;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Prezzo;

    @FXML
    private TextField Trasporto;

    @FXML
    void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    void pay(ActionEvent event) {
        
    }

}
