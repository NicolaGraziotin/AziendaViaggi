package aziendaviaggi.Controllers;

import java.net.URL;
import java.sql.ResultSet;
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

        TableV.setItems(fillTableView());
    }

    @FXML
    protected final void back(ActionEvent event) {
        changeScene(event, "/fxml/login.fxml");
    }

    protected final void cellInit(TableColumn<Pacchetto, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto,String>(value));
    }

    protected final ObservableList<Pacchetto> fillTableView () {
        ObservableList<Pacchetto> list = FXCollections.observableArrayList();
        try {
            ResultSet res = this.statement.executeQuery("SELECT * FROM PACCHETTI_TURISTICI");
            while (res.next()) {
                list.add(new Pacchetto(res.getString("Nome"), res.getString("Descrizione"), res.getString("Prezzo")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
