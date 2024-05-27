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

public class AgenziaAppController extends Controller {
    
    @FXML
    private TableView<Pacchetto> TableV;

    @FXML
    private TableColumn<Pacchetto, String> ColumnDesc;

    @FXML
    private TableColumn<Pacchetto, String> ColumnNome;

    @FXML
    private TableColumn<Pacchetto, String> ColumnPrez;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cellInit(ColumnNome, "nome");
        cellInit(ColumnDesc, "descrizione");
        cellInit(ColumnPrez, "prezzo");

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
    private void insert(ActionEvent event) {
        changeScene(event, "/fxml/agenziaInsert.fxml");
    }

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Pacchetto> selected, list;
        try {
            list = TableV.getItems();
            selected = TableV.getSelectionModel().getSelectedItems();
            for (Pacchetto pacchetto : selected) {
                list.remove(pacchetto);
            }
        } catch (Exception e) {
            alertThrower("Non ci sono piu' pacchetti!");
        }
    }

    @FXML
    private void modify(ActionEvent event) {

    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    private void cellInit(TableColumn<Pacchetto, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>(value));
    }
}
