package local.clark.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import local.clark.App;
import local.clark.tools.UserTools;


public class PrimaryController {

    @FXML public Pane paneMain;
    @FXML ImageView imViewLogo;

    public static Pane theMainPane;

    @FXML public void initialize(){
        theMainPane = paneMain;
        imViewLogo.setImage(new Image("local/clark/images/icon.png"));
    }


    @FXML public void loadAuctionList() throws IOException{
        updatePaneMain("/local/clark/ui/auction/buyer/auction.fxml");
    }

    @FXML public void loadNewLotMaker() throws IOException{
        updatePaneMain("/local/clark/ui/auction/seller/newLot.fxml");
    }
    @FXML public void loadYourLots() throws IOException{
        updatePaneMain("/local/clark/ui/auction/seller/yourLots.fxml");
    }


    @FXML public void logOut() throws IOException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out ", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            App.getStage().close();
            App.setUser(null);
        }

    }


    public static void updatePaneMain(String name) throws IOException{
        theMainPane.getChildren().clear();
        theMainPane.getChildren().add(FXMLLoader.load(PrimaryController.class.getResource(name)));
    }


    public void addContactInfo(){

        TextInputDialog dialog = new TextInputDialog(App.getUser().phoneNumber);
        dialog.setContentText("Please enter your Phone number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            App.getUser().setPhoneNumber(result.get());
            UserTools.editSelcetedUser(App.getUser());

        }

    }


}
