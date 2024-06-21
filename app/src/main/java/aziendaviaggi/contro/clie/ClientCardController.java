package aziendaviaggi.contro.clie;

import java.time.format.DateTimeFormatter;

import aziendaviaggi.contro.Controller;
import aziendaviaggi.contro.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The ClientCardController class is responsible for managing the client card functionality.
 * It handles the addition of credit card information and navigation to the client selection screen.
 */
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

    /**
     * Handles the back button click event.
     * Changes the scene to the client selection screen.
     * 
     * @param event The ActionEvent triggered by the back button click.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "ClientSelection");
    }

    /**
     * Handles the add button click event.
     * Adds the credit card information to the database.
     * 
     * @param event The ActionEvent triggered by the add button click.
     */
    @FXML
    void add(final ActionEvent event) {
        if (!checkInsert(ClientCard.getChildren())) {
            return;
        }
        executeTryBlock(() -> {
            final String codCarta = progressiveCode("CodCartaCredito", "CARTE_CREDITO", "CC");
            this.statement.executeUpdate("INSERT INTO CARTE_CREDITO " + "VALUES ("
                    + valueFormatter(codCarta) + ", "
                    + valueFormatter(LoginController.getEmailCliente()) + ", "
                    + valueFormatter(Intestatario.getText()) + ", "
                    + valueFormatter(Numero.getText()) + ", "
                    + valueFormatter(DataScadenza.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()) + ", "
                    + valueFormatter(CVV.getText())
                    + ")");
            System.out.println("Carta " + codCarta + " aggiunta con successo!");
            changeScene(event, "ClientSelection");
        });
    }
}
