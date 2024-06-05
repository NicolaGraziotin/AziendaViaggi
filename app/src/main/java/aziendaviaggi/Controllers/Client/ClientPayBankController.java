package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientPayBankController extends Controller {

    @FXML
    private Pane ClientPayBank;

    @FXML
    private TextField Importo;

    @FXML
    private DatePicker Data;

    @FXML
    private TextField Beneficiario;

    @FXML
    private TextField ContoBeneficiario;

    @FXML
    private TextField Causale;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Cognome;

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
        if (!checkInsert(ClientPayBank)) {
            return;
        }
        try {
            String codBonifico = progressiveCode("CodBonifico", "BONIFICI_BANCARI", "BB");
            this.statement.executeUpdate("INSERT INTO BONIFICI_BANCARI " + "VALUES ("
                    + valueFormatter(codBonifico) + ", "
                    + valueFormatter(Importo.getText()) + ", "
                    + valueFormatter(Data.getValue().toString()) + ", "
                    + valueFormatter(Beneficiario.getText()) + ", "
                    + valueFormatter(ContoBeneficiario.getText()) + ", "
                    + valueFormatter(Causale.getText()) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Cognome.getText())
                    + ")");
            System.out.println(codBonifico);
            changeScene(event, "clientApp");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
