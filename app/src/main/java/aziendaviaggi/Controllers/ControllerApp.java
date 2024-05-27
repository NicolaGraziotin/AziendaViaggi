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

public class ControllerApp extends Controller {

    @FXML
    protected TableView<Pacchetto> TableV;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnDesc;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnNome;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnPrez;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
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
    protected final void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    protected final void cellInit(TableColumn<Pacchetto, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>(value));
    }
}
