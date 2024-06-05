package aziendaviaggi.Controllers.Client;

import java.sql.SQLException;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientCardController extends Controller {

    @FXML
    private Pane ClientCard;

    @FXML
    private TextField Intestatario;

    @FXML
    private TextField Numero;

    @FXML
    private DatePicker DataScadenza;

    @FXML
    private TextField CVV;

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "ClientSelection");
    }

    @FXML
    void add(ActionEvent event) {
        if (!checkInsert(ClientCard)) {
            return;
        }
        try {
            String codCarta = progressiveCode("CodCartaCredito", "CARTE_CREDITO", "CC");
            this.statement.executeUpdate("INSERT INTO CARTE_CREDITO " + "VALUES ("
                    + valueFormatter(codCarta) + ", "
                    + valueFormatter(LoginController.getEmailCliente()) + ", "
                    + valueFormatter(Intestatario.getText()) + ", "
                    + valueFormatter(Numero.getText()) + ", "
                    + valueFormatter(DataScadenza.getValue().toString()) + ", "
                    + valueFormatter(CVV.getText())
                    + ")");
            System.out.println("Carta " + codCarta + " aggiunta con successo!");
            changeScene(event, "ClientSelection");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
