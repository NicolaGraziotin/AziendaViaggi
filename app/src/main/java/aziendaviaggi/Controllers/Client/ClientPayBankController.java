package aziendaviaggi.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class ClientPayBankController extends ClientPayController {

    @FXML
    private Pane ClientPayBank;

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
