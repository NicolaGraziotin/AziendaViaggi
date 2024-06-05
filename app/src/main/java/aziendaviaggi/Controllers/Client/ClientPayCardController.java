package aziendaviaggi.Controllers.Client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import aziendaviaggi.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientPayCardController extends Controller {

    @FXML
    private Pane ClientPayCard;

    @FXML
    private TextField Importo;

    @FXML
    private DatePicker Data;

    @FXML
    private TextField Intestatario;

    @FXML
    private TextField Numero;

    @FXML
    private DatePicker DataScadenza;

    @FXML
    private TextField CVV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Importo.setText(ClientSelectionController.getPrezzo());
    }

    @FXML
    void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    void pay(ActionEvent event) {
        if (!checkInsert(ClientPayCard)) {
            return;
        }
        try {
            String codCarta = progressiveCode("CodCartaCredito", "CARTE_CREDITO", "CC");
            this.statement.executeUpdate("INSERT INTO CARTE_CREDITO " + "VALUES ("
                    + valueFormatter(codCarta) + ", "
                    + valueFormatter(Importo.getText()) + ", "
                    + valueFormatter(Data.getValue().toString()) + ", "
                    + valueFormatter(Intestatario.getText()) + ", "
                    + valueFormatter(Numero.getText()) + ", "
                    + valueFormatter(DataScadenza.getValue().toString()) + ", "
                    + valueFormatter(CVV.getText())
                    + ")");
            System.out.println(codCarta);
            changeScene(event, "clientApp");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
