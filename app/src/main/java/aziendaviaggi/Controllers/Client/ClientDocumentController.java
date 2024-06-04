package aziendaviaggi.Controllers.Client;

import aziendaviaggi.Utils;
import aziendaviaggi.Controllers.Controller;
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
    private ChoiceBox<String> Tipo;

    private String cartaIdentita = "n";
    private String passaporto = "n";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tipo.getItems().addAll("Carta d'identità", "Passaporto");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "clientSelection");
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!Utils.checkInsert(ClientDocu))
            return;
        try {
            if (Tipo.getSelectionModel().getSelectedItem().equals("Carta d'identità"))
                this.cartaIdentita = "s";
            else
                this.passaporto = "s";
            this.statement.executeUpdate(
                    "INSERT INTO DOCUMENTI_VIAGGIO " + "VALUES (" + Utils.valueFormatter(NumeroDocumento.getText())
                            + ", " + Utils.valueFormatter(LuogoRilascio.getText()) + ", "
                            + Utils.valueFormatter(DataScadenza.getText())
                            + ", " + Utils.valueFormatter(this.cartaIdentita) + ", " + Utils.valueFormatter(this.passaporto)
                            + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
