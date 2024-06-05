package aziendaviaggi.Controllers.Client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import aziendaviaggi.Objects.Pacchetto;

public class ClientReviewController extends Controller {
    
    @FXML
    private Pane ClientReview;

    @FXML
    private TextField Pacchetto;

    @FXML
    private TextField Email;

    @FXML
    private TextField Voto;

    @FXML
    private TextArea Commento;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pacchetto selected = ClientAppController.getActual();
        Pacchetto.setText(selected.getCodPacchetto());
        Email.setText(LoginController.getEmailCliente());
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "ClientApp");
    }

    @FXML
    private void post(ActionEvent event) {
        if (!checkInsert(ClientReview)) {
            return;
        }
        try {
            String codRecensione = progressiveCode("CodRecensione", "RECENSIONI", "R");
            statement.executeUpdate("INSERT INTO RECENSIONI VALUES ("
                    + valueFormatter(codRecensione) + ", "
                    + valueFormatter(Pacchetto.getText()) + ", "
                    + valueFormatter(Email.getText()) + ", "
                    + valueFormatter(Voto.getText()) + ", "
                    + valueFormatter(Commento.getText()) + ")");
            System.out.println("Recensione " + codRecensione + " inserita con successo");
            changeScene(event, "ClientApp");
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
