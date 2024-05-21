package aziendaviaggi.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class AgenziaAppController extends ControllerFactory {

    @FXML
    private Pane AgenziaAppl;

    @FXML
    private ListView<String> ListV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList("Ciao", "Dio", "Cane");
        ListV.setItems(items);
    }

    @FXML
    private void insert(ActionEvent event) {
        changeScene(event, "/agenziaInsert.fxml");
    }

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<String> items = ListV.getItems();
        if (items.isEmpty()) {
            alertThrower("Non ci sono piu' pacchetti!");
        } else {
            String deleting = ListV.getSelectionModel().getSelectedItem();
            items.remove(deleting);
        }
    }

    @FXML
    private void modify(ActionEvent event) {

    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/agenziaRegistration.fxml");
    }
}
