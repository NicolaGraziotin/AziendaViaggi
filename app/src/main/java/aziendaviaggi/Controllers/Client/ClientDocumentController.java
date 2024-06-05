package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientDocumentController extends Controller {

    @FXML
    private Pane ClientDocument;

    @FXML
    private TextField NumeroDocumento;

    @FXML
    private TextField LuogoRilascio;

    @FXML
    private TextField DataScadenza;

    @FXML
    private TextField Email;

    @FXML
    private ChoiceBox<String> Tipo;

    private String cartaIdentita = "N";
    private String passaporto = "N";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Email.setText(LoginController.getEmailCliente());
        Tipo.getItems().addAll("Carta d'identita", "Passaporto");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "ClientSelection");
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(ClientDocument))
            return;
        try {
            if (Tipo.getSelectionModel().getSelectedItem().equals("Carta d'identita"))
                this.cartaIdentita = "S";
            else
                this.passaporto = "S";
            this.statement.executeUpdate(
                    "INSERT INTO DOCUMENTI_VIAGGIO " + "VALUES (" + valueFormatter(NumeroDocumento.getText()) + ", "
                            + valueFormatter(Email.getText()) + ", "
                            + valueFormatter(LuogoRilascio.getText()) + ", "
                            + valueFormatter(DataScadenza.getText()) + ", "
                            + valueFormatter(this.cartaIdentita) + ", "
                            + valueFormatter(this.passaporto)
                            + ")");
            System.out.println("Documento " + NumeroDocumento.getText() + " registrato con successo.");
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
