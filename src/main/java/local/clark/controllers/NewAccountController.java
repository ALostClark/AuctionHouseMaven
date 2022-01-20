package local.clark.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import local.clark.App;
import local.clark.entries.User;
import local.clark.tools.UserTools;

public class NewAccountController {


    @FXML ImageView imViewLogo;

    @FXML private PasswordField textBoxPassword;
    @FXML private TextField textBoxUserName;
    @FXML private TextField textBoxUserPhoneNumber;

    @FXML public void initialize(){
        imViewLogo.setImage(new Image("local/clark/images/icon.png"));
    }

    public void newUser ()  {


        UserTools userTools = new UserTools();

        String tbPassword = textBoxPassword.getText();
        String tbUserName = textBoxUserName.getText();
        String tbPhoneNumber = textBoxUserPhoneNumber.getText();

        String hashedPassword = userTools.hashedPassword(tbPassword);


        //update to get new number
        int fn = 1;

        User newuser = new User(fn, tbUserName, hashedPassword, tbPhoneNumber );


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to make an account called " + tbUserName + "?", ButtonType.YES , ButtonType.CANCEL);
        alert.showAndWait();

        newuser.username = textBoxUserName.getText();


        try {
            User qI = UserTools.checkSpaceUser(newuser);
            if (qI == null) {

                if (alert.getResult() == ButtonType.YES) {
                    try {
                        UserTools.newSpaceUser(newuser);
                        App.setRoot("login");
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to Make an account check your connection", ButtonType.OK);
                    }
                }
            }else {

                Alert alert2 = new Alert(Alert.AlertType.WARNING, "This user name has been taken", ButtonType.OK);
                alert2.showAndWait();
            }
        }catch (Exception e){}


    }

}
