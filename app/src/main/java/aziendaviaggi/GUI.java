package aziendaviaggi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The GUI class is responsible for creating and displaying the graphical user interface of the Azienda Viaggi application.
 */
public class GUI extends Application {

    /**
     * The start method is called when the application is launched. It sets up the main stage, loads the login screen,
     * sets the application icon, and handles the close request event.
     *
     * @param primaryStage the primary stage of the application
     * @throws Exception if an error occurs during the initialization of the GUI
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        new SQLDatabaseConnection().connect();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        final Parent root = loader.load();
        final Image icon = new Image("/logo.png");

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Azienda Viaggi");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> {
            if (!Utils.confirmThrower("Sei sicuro di volere uscire?")) {
                event.consume();
            }
        });
        primaryStage.show();
    }
}
