package local.clark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import local.clark.entries.Lot;
import local.clark.entries.User;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static User userTemp = null;
    private static Lot lotTemp = null;
    private static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        App.stage = stage;

        stage.setScene(scene);
        stage.setTitle("Auction house");
        stage.getIcons().add(new Image("local/clark/images/icon.png"));
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ui/" +fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


    public static User getUser() {
        return userTemp;
    }

    public static void setUser(User user) throws IOException {
        userTemp = user;
    }

    public static Lot getLotTemp() {
        return lotTemp;
    }

    public static void setLotTemp(Lot lot) throws IOException {
        lotTemp = lot;
    }


}
