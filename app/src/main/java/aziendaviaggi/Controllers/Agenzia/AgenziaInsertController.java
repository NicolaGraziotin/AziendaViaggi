package aziendaviaggi.Controllers.Agenzia;

import java.net.URL;
import java.util.ResourceBundle;

import aziendaviaggi.Controllers.Controller;
import aziendaviaggi.Controllers.LoginController;
import aziendaviaggi.Objects.Attivita;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class AgenziaInsertController extends Controller {

    @FXML
    private Pane AgenziaInsert;

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
    private TextField Agenzia;

    @FXML
    private TableView<Attivita> TableAttivita;

    @FXML
    private TableColumn<Attivita, String> ColumnNome;
    
    @FXML
    private TableColumn<Attivita, String> ColumnDescrizione;
    
    @FXML
    private TableColumn<Attivita, String> ColumnOrario;
    
    @FXML
    private TableColumn<Attivita, String> ColumnDurata;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Agenzia.setText(LoginController.getCodAgenzia());
        choiceBoxInit("CodAlloggio", "ALLOGGI", Alloggio);
        choiceBoxInit("CodDestinazione", "DESTINAZIONI", Destinazione);
        Guida.getItems().add("NULL");
        choiceBoxInit("CodGuida", "GUIDE_TURISTICHE", Guida);
        choiceBoxInit("CodTrasporto", "TRASPORTI", Trasporto);

        cellInit(ColumnNome, "Nome");
        cellInit(ColumnDescrizione, "Descrizione");
        cellInit(ColumnOrario, "Orario");
        cellInit(ColumnDurata, "Durata");

        TableAttivita.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableAttivita.setItems(fillTableView());
    }

    @FXML
    private void enter(ActionEvent event) {
        if (!checkInsert(AgenziaInsert))
            return;
        try {
            String codPacchetto = progressiveCode("CodPacchetto", "PACCHETTI_TURISTICI", "PT");
            this.statement.executeUpdate("INSERT INTO PACCHETTI_TURISTICI VALUES ("
                    + valueFormatter(codPacchetto) + ", "
                    + valueFormatter(Nome.getText()) + ", "
                    + valueFormatter(Descrizione.getText()) + ", "
                    + valueFormatter(Prezzo.getText()) + ", "
                    + valueFormatter(LoginController.getCodAgenzia()) + ", "
                    + guidaCheck(Guida.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Trasporto.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Alloggio.getSelectionModel().getSelectedItem()) + ", "
                    + valueFormatter(Destinazione.getSelectionModel().getSelectedItem())
                    + ")");
            System.out.println("Pacchetto: " + codPacchetto + " aggiunto.");
            ObservableList<Attivita> selectedItems = TableAttivita.getSelectionModel().getSelectedItems();
            for (Attivita attivita : selectedItems) {
                this.statement.executeUpdate("INSERT INTO ITINERARI VALUES ("
                        + valueFormatter(codPacchetto) + ", "
                        + valueFormatter(attivita.getCodAttivita())
                        + ")");
                System.out.println("Attivita: " + attivita.getCodAttivita() + " aggiunta al pacchetto " + codPacchetto + ".");
            }
            back(event);
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        changeScene(event, "AgenziaApp");
    }

    private ObservableList<Attivita> fillTableView() {
        ObservableList<Attivita> list = FXCollections.observableArrayList();
        try {
            ResultSet res = this.statement.executeQuery("SELECT * FROM ATTIVITA");
            while (res.next()) {
                list.add(new Attivita(res.getString("CodAttivita"), res.getString("Nome"), res.getString("Descrizione"),
                        res.getString("Orario"), res.getString("Durata")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private final void cellInit(TableColumn<Attivita, String> cell, String value) {
        cell.setCellValueFactory(new PropertyValueFactory<Attivita, String>(value));
    }

    protected String guidaCheck(String guida) {
        return guida == "NULL" ? guida : valueFormatter(guida);
    }

    protected void choiceBoxInit(String column, String table, ChoiceBox<String> choice) {
        try {
            ResultSet res = this.statement.executeQuery("SELECT " + column + " FROM " + table);
            while (res.next()) {
                choice.getItems().add(res.getString(column));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
