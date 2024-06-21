package aziendaviaggi.controllers.client;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The ClientBankController class is responsible for handling the user
 * interactions and logic related to the client bank operations.
 */
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

    /**
     * Handles the back button click event.
     * Changes the scene to the ClientSelection view.
     * 
     * @param event The ActionEvent object representing the button click event.
     */
    @FXML
    void back(final ActionEvent event) {
        changeScene(event, "ClientSelection");
    }

    /**
     * Handles the add button click event.
     * Inserts a new bank transfer record into the database.
     * 
     * @param event The ActionEvent object representing the button click event.
     */
    @FXML
    void add(final ActionEvent event) {
        if (!checkInsert(ClientBank.getChildren())) {
            return;
        }
        executeTryBlock(() -> {
            final String codBonifico = progressiveCode("CodBonifico", "BONIFICI_BANCARI", "BB");
            this.statement.executeUpdate("INSERT INTO BONIFICI_BANCARI " + "VALUES ("
                    + valueFormatter(codBonifico) + ", "
                    + valueFormatter(LoginController.getEmailCliente()) + ", "
                    + valueFormatter(NomeOrdinante.getText()) + ", "
                    + valueFormatter(ContoOrdinante.getText()) + ", "
                    + valueFormatter(NomeBeneficiario.getText()) + ", "
                    + valueFormatter(ContoBeneficiario.getText()) + ", "
                    + valueFormatter(Causale.getText())
                    + ")");
            System.out.println("Bonifico " + codBonifico + " aggiunto con successo!");
            changeScene(event, "ClientSelection");
        });
    }
}
