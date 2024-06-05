package aziendaviaggi.Controllers.Client;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClientPayCardController extends ClientPayController {

    @FXML
    private Pane ClientPayCard;

    @FXML
    private TextField Intestatario;

    @FXML
    private TextField Numero;

    @FXML
    private DatePicker DataScadenza;

    @FXML
    private TextField CVV;

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
