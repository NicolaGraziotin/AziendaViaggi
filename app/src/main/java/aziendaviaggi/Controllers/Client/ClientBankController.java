package aziendaviaggi.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;

public class ClientBankController extends Controller {

    @FXML
    private Pane ClientBank;

    @FXML
    private TextField NomeOrdinante;

    @FXML
    private TextField ContoOrdinante;

    @FXML
    private TextField NomeBeneficiario;

    @FXML
    private TextField ContoBeneficiario;

    @FXML
    private TextArea Causale;

    @FXML
    void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    void add(ActionEvent event) {
        if (!checkInsert(ClientBank)) {
            return;
        }
        try {
            String codBonifico = progressiveCode("CodBonifico", "BONIFICI_BANCARI", "BB");
            this.statement.executeUpdate("INSERT INTO BONIFICI_BANCARI " + "VALUES ("
                    + valueFormatter(codBonifico) + ", "
                    + valueFormatter(LoginController.EmailCliente) + ", "
                    + valueFormatter(NomeOrdinante.getText()) + ", "
                    + valueFormatter(ContoOrdinante.getText()) + ", "
                    + valueFormatter(NomeBeneficiario.getText()) + ", "
                    + valueFormatter(ContoBeneficiario.getText()) + ", "
                    + valueFormatter(Causale.getText())
                    + ")");
            System.out.println("Bonifico " + codBonifico + " aggiunto con successo!");
            changeScene(event, "clientSelection");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
