package aziendaviaggi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        new SQLDatabaseConnection().connect();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Image icon = new Image("/logo.png");

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Azienda Viaggi");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> {
            if (!Utils.confirmThrower("Sei sicuro di volere uscire?"))
                event.consume();
        });
        primaryStage.show();
    }
}
