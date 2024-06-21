package aziendaviaggi.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import aziendaviaggi.SQLDatabaseConnection;
import aziendaviaggi.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The Controller class is responsible for handling user interactions and
 * controlling the application logic.
 */
public class Controller implements Initializable {

    protected Statement statement = SQLDatabaseConnection.getStatement();

    /**
     * Initializes the controller.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }

    /**
     * Changes the scene to the specified panel.
     *
     * @param event The event that triggered the scene change.
     * @param panel The name of the panel to change to.
     */
    protected void changeScene(final ActionEvent event, final String panel) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + panel + ".fxml"));
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = loader.load();
            stage.setScene(Utils.resizeScene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an alert with the specified message.
     *
     * @param msg The message to display in the alert.
     */
    protected void alertThrower(final String msg) {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Formats a string value by adding single quotes around it.
     *
     * @param msg The string value to format.
     * @return The formatted string value.
     */
    protected String valueFormatter(final String msg) {
        return "'" + msg + "'";
    }

    /**
     * Checks if all required fields in the specified pane are filled.
     *
     * @param pane The pane containing the fields to check.
     * @return true if all required fields are filled, false otherwise.
     */
    @SuppressWarnings("unchecked")
    protected boolean checkInsert(final ObservableList<Node> children) {
        for (final Node elem : children) {
            if (elem instanceof TextField && ((TextField) elem).getText().isEmpty()) {
                alertThrower("Inserisci " + elem.getId());
                return false;
            } else if (elem instanceof ChoiceBox
                    && ((ChoiceBox<String>) elem).getSelectionModel().getSelectedItem() == null) {
                alertThrower("Seleziona " + elem.getId());
                return false;
            } else if (elem instanceof TextArea && ((TextArea) elem).getText().isEmpty()) {
                alertThrower("Inserisci " + elem.getId());
                return false;
            } else if (elem instanceof DatePicker && ((DatePicker) elem).getValue() == null) {
                alertThrower("Scegli " + elem.getId());
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a progressive code based on the maximum value of the specified
     * column in the specified table.
     *
     * @param column The column to get the maximum value from.
     * @param table  The table to query for the maximum value.
     * @param prefix The prefix to prepend to the generated code.
     * @return The generated progressive code.
     */
    protected String progressiveCode(final String column, final String table, final String prefix) {
        String num = "0";
        try {
            final ResultSet res = this.statement.executeQuery("SELECT MAX(" + column + ") AS Max FROM " + table);
            if (res.next() && res.getString("Max") != null) {
                num = res.getString("Max").replaceAll("\\D", "");
            }
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + String.format("%03d", Integer.parseInt(num) + 1);
    }

    @FunctionalInterface
    protected interface TryBlock {
        void execute() throws SQLException, Exception;
    }

    /**
     * Executes the provided TryBlock by invoking its execute() method within a
     * try-catch block.
     * If a SQLException is caught, it calls the alertThrower() method with the
     * error message.
     * If any other exception is caught, it prints the stack trace.
     *
     * @param block the TryBlock to execute
     */
    protected void executeTryBlock(final TryBlock block) {
        try {
            block.execute();
        } catch (SQLException e) {
            alertThrower(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the details of a document based on the given document number.
     *
     * @param Documento  the document number to search for
     * @param Specifiche the TextArea where the document details will be displayed
     */
    protected void printDocumento(final String Documento, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM DOCUMENTI_VIAGGIO WHERE NumeroDocumento = " + valueFormatter(Documento));
            if (res.next()) {
                Specifiche.setText("DOCUMENTO:\n"
                        + "Luogo rilascio: " + res.getString("LuogoRilascio") + "\n"
                        + "Data scadenza: " + res.getString("DataScadenza") + "\n"
                        + "Passaporto: " + res.getString("PASSAPORTO") + "\n"
                        + "Carta d'identita: " + res.getString("CARTA_IDENTITA"));
            }
        });
    }

    protected final void printGuida(final String Guida, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement
                    .executeQuery("SELECT * FROM GUIDE_TURISTICHE WHERE CodGuida = " + valueFormatter(Guida));
            if (res.next()) {
                Specifiche.setText("GUIDA:\n"
                        + "Nome: " + res.getString("Nome") + "\n"
                        + "Cognome: " + res.getString("Cognome") + "\n"
                        + "Lingua: " + res.getString("Lingua") + "\n"
                        + "Esperienza: " + res.getString("Esperienza"));
            }
        });
    }

    protected void printTrasporto(final String Trasporto, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM TRASPORTI WHERE CodTrasporto = " + valueFormatter(Trasporto));
            if (res.next()) {
                Specifiche.setText("TRASPORTO:\n"
                        + "Compagnia: " + res.getString("Compagnia") + "\n"
                        + "Partenza: " + res.getString("Partenza") + "\n"
                        + "Destinazione: " + res.getString("Destinazione") + "\n"
                        + "Orario: " + res.getString("Orario") + "\n"
                        + "Traghetto: " + res.getString("TRAGHETTO") + "\n"
                        + "Autobus: " + res.getString("AUTOBUS") + "\n"
                        + "Aereo: " + res.getString("AEREO"));
            }
        });
    }

    protected final void printAlloggio(final String Alloggio, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement
                    .executeQuery("SELECT * FROM ALLOGGI WHERE CodAlloggio = " + valueFormatter(Alloggio));
            if (res.next()) {
                Specifiche.setText("ALLOGGIO:\n"
                        + "Nome: " + res.getString("Nome") + "\n"
                        + "Citta: " + res.getString("Ind_Citta") + "\n"
                        + "Via: " + res.getString("Ind_Via") + "\n"
                        + "Numero civico: " + res.getString("Ind_NumeroCivico") + "\n"
                        + "Numero camere: " + res.getString("NumeroCamere") + "\n"
                        + "Hotel: " + res.getString("HOTEL") + "\n"
                        + "Formula: " + res.getString("Formula") + "\n"
                        + "Appartamento: " + res.getString("APPARTAMENTO"));
            }
        });
    }

    protected void printDestinazione(final String Destinazione, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM DESTINAZIONI WHERE CodDestinazione = " + valueFormatter(Destinazione));
            if (res.next()) {
                Specifiche.setText("DESTINAZIONE:\n"
                        + "Paese: " + res.getString("Paese") + "\n"
                        + "Citta: " + res.getString("Citta") + "\n"
                        + "Descrizione: " + res.getString("Descrizione"));
            }
        });
    }

    protected void printAssicurazione(final String Assicurazione, final TextArea Specifiche) {
        executeTryBlock(() -> {
            final ResultSet res = this.statement.executeQuery(
                    "SELECT * FROM ASSICURAZIONI WHERE CodAssicurazione = " + valueFormatter(Assicurazione));
            if (res.next()) {
                Specifiche.setText("ASSICURAZIONE:\n"
                        + "Tipo: " + res.getString("Tipo") + "\n"
                        + "Copertura: " + res.getString("Copertura") + "\n"
                        + "Prezzo: " + res.getString("Prezzo"));
            }
        });
    }

    protected void printMetodo(final String Metodo, final TextArea Specifiche) {
        executeTryBlock(() -> {
            if (Metodo.startsWith("CC")) {
                final ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM CARTE_CREDITO WHERE CodCartaCredito = " + valueFormatter(Metodo));
                if (res.next()) {
                    Specifiche.setText("CARTA DI CREDITO:\n"
                            + "Intestatario: " + res.getString("Intestatario") + "\n"
                            + "Numero carta: " + res.getString("Numero") + "\n"
                            + "Data scadenza: " + res.getString("DataScadenza") + "\n"
                            + "CVV: " + res.getString("CVV"));
                }
            } else {
                final ResultSet res = this.statement.executeQuery(
                        "SELECT * FROM BONIFICI_BANCARI WHERE CodBonifico = " + valueFormatter(Metodo));
                if (res.next()) {
                    Specifiche.setText("BONIFICO BANCARIO:\n"
                            + "Nome ordinante: " + res.getString("NomeOrdinante") + "\n"
                            + "Conto ordinante: " + res.getString("ContoOrdinante") + "\n"
                            + "Nome beneficiario: " + res.getString("NomeBeneficiario") + "\n"
                            + "Conto beneficiario: " + res.getString("ContoBeneficiario") + "\n"
                            + "Causale: " + res.getString("Causale"));
                }
            }
        });
    }

    protected <T> void cellInit(final TableColumn<T, String> cell, final String value) {
        cell.setCellValueFactory(new PropertyValueFactory<T, String>(value));
    }
}
