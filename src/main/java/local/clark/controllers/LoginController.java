package local.clark.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import local.clark.App;
import local.clark.entries.User;
import local.clark.tools.UserTools;

import java.io.IOException;


public class LoginController {

    @FXML private ImageView imViewLogo;
    @FXML private PasswordField textBoxPassword;
    @FXML private TextField textBoxUserName;
    @FXML private Button buttonLogin;


    @FXML public void initialize(){
        imViewLogo.setImage(new Image("local/clark/images/icon.png"));

        textBoxPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });



    }


    public void newUser() throws IOException {
        App.setRoot("NewAccount");



    }


    public void login () {

        UserTools userTools = new UserTools();

        String tbPassword = textBoxPassword.getText();

        User newuser = new User();
        newuser.username = textBoxUserName.getText();

        //Read
        try {
            User qI = UserTools.checkSpaceUser(newuser);
            if (qI == null){
               // System.out.println("Fail 1");
            }else {

                if (userTools.hashedPassword(tbPassword).equals(qI.hashedPassword)) {
                  //  System.out.println("we in");

                    App.setUser(qI);

                    Scene scene;

                    scene = new Scene(FXMLLoader.load(getClass().getResource("/local/clark/ui/primary.fxml")));
                    App.getStage().setScene(scene);
                    App.getStage().setTitle("Auction house - Home");
                    App.getStage().getIcons().add(new Image("local/clark/images/icon.png"));
                    App.getStage().show();

                    App.getStage().setWidth(1600);
                    App.getStage().setHeight(900);
                } else {

                    Alert alert = new Alert(Alert.AlertType.WARNING, "Incorrect Password Entered!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

    }






}
