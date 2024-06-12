package aziendaviaggi.controllers.client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import aziendaviaggi.controllers.Controller;
import aziendaviaggi.controllers.LoginController;
import aziendaviaggi.objects.Pacchetto;

/**
 * The ClientReviewController class is responsible for managing the client review functionality.
 * It allows the client to post a review for a specific package.
 */
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

    /**
     * Initializes the controller.
     * Sets the selected package and client's email in the respective text fields.
     * 
     * @param location The URL location of the FXML file.
     * @param resources The ResourceBundle for the FXML file.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final Pacchetto selected = ClientAppController.getActual();
        Pacchetto.setText(selected.getCodPacchetto());
        Email.setText(LoginController.getEmailCliente());
    }

    /**
     * Handles the back button action.
     * Changes the scene to the client application scene.
     * 
     * @param event The ActionEvent triggered by the back button.
     */
    @FXML
    private void back(final ActionEvent event) {
        changeScene(event, "ClientApp");
    }

    /**
     * Handles the post button action.
     * Inserts the review into the database if all the required fields are filled.
     * Changes the scene to the client application scene.
     * 
     * @param event The ActionEvent triggered by the post button.
     */
    @FXML
    private void post(final ActionEvent event) {
        if (!checkInsert(ClientReview)) {
            return;
        }
        executeTryBlock(() -> {
            statement.executeUpdate("INSERT INTO RECENSIONI VALUES ("
                    + valueFormatter(Pacchetto.getText()) + ", "
                    + valueFormatter(Email.getText()) + ", "
                    + valueFormatter(Voto.getText()) + ", "
                    + valueFormatter(Commento.getText()) + ")");
            System.out.println("Recensione inserita con successo");
            changeScene(event, "ClientApp");
        });
    }
}
