package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientPayCardController extends Controller {

    @FXML
    private Pane ClientPayCard;

    @FXML
    private TextField Importo;

    @FXML
    private TextField Data;

    @FXML
    private TextField Intestatario;

    @FXML
    private TextField Numero;

    @FXML
    private TextField DataScadenza;

    @FXML
    private TextField CVV;

    @FXML
    void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    void pay(ActionEvent event) {
        
    }

}
