package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Utils;
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
    private Pane ClientDocu;

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

    private String cartaIdentita = "n";
    private String passaporto = "n";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Email.setText(LoginController.EmailCliente);
        Tipo.getItems().addAll("Carta d'identità", "Passaporto");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(ClientDocu))
            return;
        try {
            if (Tipo.getSelectionModel().getSelectedItem().equals("Carta d'identità"))
                this.cartaIdentita = "s";
            else
                this.passaporto = "s";
            this.statement.executeUpdate(
                    "INSERT INTO DOCUMENTI_VIAGGIO " + "VALUES (" + valueFormatter(NumeroDocumento.getText()) + ", "
                            + valueFormatter(Email.getText()) + ", "
                            + valueFormatter(LuogoRilascio.getText()) + ", "
                            + valueFormatter(DataScadenza.getText()) + ", "
                            + valueFormatter(this.cartaIdentita) + ", "
                            + valueFormatter(this.passaporto)
                            + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
