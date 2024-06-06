package aziendaviaggi.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import aziendaviaggi.Objects.Attivita;
import aziendaviaggi.Objects.Pacchetto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;

public class ControllerApp extends Controller {

    @FXML
    protected TableView<Pacchetto> TablePacchetti;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnAgenzia;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnDescrizione;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnNome;

    @FXML
    protected TableColumn<Pacchetto, String> ColumnPrezzo;

    @FXML
    protected TextField Guida;

    @FXML
    protected TextField Trasporto;

    @FXML
    protected TextField Alloggio;

    @FXML
    protected TextField Destinazione;

    @FXML
    protected TableView<Attivita> TableAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnNomeAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnDescrizioneAttivita;

    @FXML
    protected TableColumn<Attivita, String> ColumnOrario;

    @FXML
    protected TableColumn<Attivita, String> ColumnDurata;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        cellInitPac(ColumnAgenzia, "CodAgenzia");
        cellInitPac(ColumnNome, "Nome");
        cellInitPac(ColumnDescrizione, "Descrizione");
        cellInitPac(ColumnPrezzo, "Prezzo");

        cellInitAtt(ColumnNomeAttivita, "Nome");
        cellInitAtt(ColumnDescrizioneAttivita, "Descrizione");
        cellInitAtt(ColumnOrario, "Orario");
        cellInitAtt(ColumnDurata, "Durata");

        TablePacchetti.setItems(fillTableView());
    }

    @FXML
    protected final void back(ActionEvent event) {
        changeScene(event, "Login");
    }

    @FXML
    protected final void update(MouseEvent event) {
        Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
        Guida.setText(sel.getCodGuida());
        Trasporto.setText(sel.getCodTrasporto());
        Alloggio.setText(sel.getCodAlloggio());
        Destinazione.setText(sel.getCodDestinazione());
        TableAttivita.setItems(fillAttivita());
    }

    protected void cellInitPac(TableColumn<Pacchetto, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Pacchetto, String>(value));
    }
    
    protected void cellInitAtt(TableColumn<Attivita, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Attivita, String>(value));
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
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private ObservableList<Attivita> fillAttivita() {
        ObservableList<Attivita> list = FXCollections.observableArrayList();
        try {
            Pacchetto sel = TablePacchetti.getSelectionModel().getSelectedItem();
            ResultSet res = this.statement.executeQuery(
                    "SELECT A.* " +
                    "FROM ITINERARI I RIGHT JOIN ATTIVITA A ON(I.CodAttivita = A.CodAttivita) " +
                    "WHERE I.CodPacchetto = " + valueFormatter(sel.getCodPacchetto()));
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"),
                        res.getString("Descrizione"), res.getString("Orario"),
                        res.getString("Durata")));
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
