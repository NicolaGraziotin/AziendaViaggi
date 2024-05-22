package aziendaviaggi.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AgenziaInsertController extends ControllerFactory {

    @FXML
    private Pane AgenziaInse;

    @FXML
    private TextField Descrizione;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Prezzo;

    @FXML
    private ChoiceBox<String> Alloggio;

    @FXML
    private ChoiceBox<String> Destinazione;

    @FXML
    private ChoiceBox<String> Guida;

    @FXML
    private ChoiceBox<String> Trasporto;

    @FXML
    private void enter(ActionEvent event) {
        /*
         * Control for empty TextField or ChoiceBox
         */
        for (Node node : AgenziaInse.getChildren()) {
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()
                || node instanceof ChoiceBox<?> && ((ChoiceBox<?>) node).getSelectionModel().getSelectedItem() == null) {
                alertThrower("Inserisci " + node.getId() + " valido!");
                return;
            }
        }

        System.out.println("daje");
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/agenziaApp.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alloggio.getItems().addAll("Casa", "Hotel");
        Destinazione.getItems().addAll("Italia", "Spagna");
        Guida.getItems().addAll("Nicola", "Roberta");
        Trasporto.getItems().addAll("Aereo", "Traghetto");
    }
}
