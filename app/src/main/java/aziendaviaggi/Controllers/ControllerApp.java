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
import javafx.scene.input.MouseEvent;

public class ControllerApp extends Controller {

    @FXML
    protected TableView<Pacchetto> TableV;

    @FXML
    protected TableView<Pacchetto> TableV2;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnAgen;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnDesc;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnNome;
    
    @FXML
    protected TableColumn<Pacchetto, String> ColumnPrez;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnGuid;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnTras;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnAllo;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnDest;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        cellInit(ColumnAgen, "codAgenzia");
        cellInit(ColumnNome, "nome");
        cellInit(ColumnDesc, "descrizione");
        cellInit(ColumnPrez, "prezzo");

        cellInit(ColumnGuid, "codGuida");
        cellInit(ColumnTras, "codTrasporto");
        cellInit(ColumnAllo, "codAlloggio");
        cellInit(ColumnDest, "codDestinazione");

        TableV.setItems(fillTableView());
    }

    @FXML
    protected final void back(ActionEvent event) {
        changeScene(event, "login");
    }

    @FXML
    protected final void update(MouseEvent event) {
        Pacchetto sel = TableV.getSelectionModel().getSelectedItem();
        ObservableList<Pacchetto> list = FXCollections.observableArrayList(sel);
        TableV2.setItems(list);
    }

    protected final void cellInit(TableColumn<Pacchetto, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto, String>(value));
    }

    protected final ObservableList<Pacchetto> fillTableView() {
        ObservableList<Pacchetto> list = FXCollections.observableArrayList();
        try {
            ResultSet res = this.statement.executeQuery("SELECT * FROM PACCHETTI_TURISTICI");
            while (res.next()) {
                list.add(new Pacchetto(res.getString("CodPacchetto"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Prezzo"), res.getString("CodAgenzia"),
                        res.getString("CodGuida"), res.getString("CodTrasporto"), res.getString("CodAlloggio"),
                        res.getString("CodDestinazione")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
