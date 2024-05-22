package aziendaviaggi.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.Objects.Pacchetto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientAppController extends ControllerFactory {

    @FXML
    private TableView<Pacchetto> TableV;

    @FXML
    private TableColumn<Pacchetto, String> ColumnNome;

    @FXML
    private TableColumn<Pacchetto, String> ColumnDesc;

    @FXML
    private TableColumn<Pacchetto, String> ColumnPrez;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ColumnNome.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>("nome"));
        ColumnDesc.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>("descrizione"));
        ColumnPrez.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>("prezzo"));

        ObservableList<Pacchetto> list = FXCollections.observableArrayList(
            new Pacchetto("Hawaii", "Isola con spiaggia privata", "1000"),
            new Pacchetto("Hawaii", "Isola con spiaggia privata", "1000"),
            new Pacchetto("Hawaii", "Isola con spiaggia privata", "1000"),
            new Pacchetto("Hawaii", "Isola con spiaggia privata", "1000"),
            new Pacchetto("Hawaii", "Isola con spiaggia privata", "1000")
        );

        TableV.setItems(list);
    }

    @FXML
    private void select(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/login.fxml");
    }
}
