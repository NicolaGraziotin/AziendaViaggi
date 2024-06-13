package aziendaviaggi.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import aziendaviaggi.SQLDatabaseConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
            stage.setScene(new Scene(loader.load()));
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
     * Executes the provided TryBlock by invoking its execute() method within a try-catch block.
     * If a SQLException is caught, it calls the alertThrower() method with the error message.
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
}
