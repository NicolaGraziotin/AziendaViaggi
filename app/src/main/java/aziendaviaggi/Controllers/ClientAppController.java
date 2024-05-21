package aziendaviaggi.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ClientAppController extends ControllerFactory {

    @FXML
    private Pane ClientAppl;

    @FXML
    private ListView<String> ListV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList("Ciao", "Dio", "Cane");
        ListV.setItems(items);
    }

    @FXML
    private void select(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/clientRegistration.fxml");
    }
}
