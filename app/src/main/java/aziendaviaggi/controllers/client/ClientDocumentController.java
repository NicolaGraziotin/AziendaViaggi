package aziendaviaggi.controllers.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;

/**
 * This class is the controller for the client document view.
 * It handles the user interactions and data manipulation related to client documents.
 */
public class ClientDocumentController extends Controller {

    @FXML
    private Pane ClientDocument;

    @FXML
    private TextField NumeroDocumento;

    @FXML
    private TextField LuogoRilascio;

    @FXML
    private DatePicker DataScadenza;

    @FXML
    private TextField Email;

    @FXML
    private ChoiceBox<String> Tipo;

    private String cartaIdentita = "N";
    private String passaporto = "N";

    /**
     * Initializes the controller.
     *
     * @param location  The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Email.setText(LoginController.getEmailCliente());
        Tipo.getItems().addAll("Carta d'identita", "Passaporto");
    }

    /**
     * Handles the back button action.
     *
     * @param event The event triggered by the back button.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "ClientSelection");
    }

    /**
     * Handles the enter button action.
     *
     * @param event The event triggered by the enter button.
     */
    @FXML
    private void enter(final ActionEvent event) {
        if (!checkInsert(ClientDocument.getChildren())) {
            return;
        }
        executeTryBlock(() -> {
            if (Tipo.getSelectionModel().getSelectedItem().equals("Carta d'identita")) {
                this.cartaIdentita = "S";
            } else {
                this.passaporto = "S";
            }
            this.statement.executeUpdate(
                    "INSERT INTO DOCUMENTI_VIAGGIO " + "VALUES (" + valueFormatter(NumeroDocumento.getText()) + ", "
                            + valueFormatter(Email.getText()) + ", "
                            + valueFormatter(LuogoRilascio.getText()) + ", "
                            + valueFormatter(DataScadenza.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()) + ", "
                            + valueFormatter(this.passaporto) + ", "
                            + valueFormatter(this.cartaIdentita)
                            + ")");
            System.out.println("Documento " + NumeroDocumento.getText() + " registrato con successo.");
            back(event);
        });
    }
}
