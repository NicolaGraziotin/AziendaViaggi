package aziendaviaggi.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ClientPayController extends Controller {

    @FXML
    protected TextField Importo;

    @FXML
    protected DatePicker Data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Importo.setText(ClientSelectionController.getPrezzo());
    }
    
    @FXML
    void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

}
