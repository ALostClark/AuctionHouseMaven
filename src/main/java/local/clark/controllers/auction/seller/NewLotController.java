package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import local.clark.App;
import local.clark.controllers.PrimaryController;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.time.LocalDate;
import java.util.UUID;

public class NewLotController {


    @FXML
    CheckBox newLotTickBoxBuyOut;

    @FXML
    TextField newLotTFBuyOutPrice;
    @FXML
    TextField newLotTFItemName;
    @FXML
    TextField newLotTFStartingAmount;

    @FXML
    Label newLotLBUserName;

    @FXML
    DatePicker newLotDatePickerEndDate;

    @FXML
    TextArea newLotTADescription;

    @FXML public void initialize(){
        newLotLBUserName.setText(App.getUser().username);

    }

    public void checkBoxUpdate(){
        if (newLotTickBoxBuyOut.isSelected()){
            newLotTFBuyOutPrice.setVisible(true);
        }else{
            newLotTFBuyOutPrice.setVisible(false);
        }
    }

    public void startNewLot(){

        String lotID = UUID.randomUUID().toString();
        String userName = App.getUser().username;
        String newLotName = newLotTFItemName.getText();

        LocalDate endDate = newLotDatePickerEndDate.getValue();
        LocalDate startDate = LocalDate.now();

        String stBuyOutPrice = newLotTFBuyOutPrice.getText();
        String statingPrice = newLotTFStartingAmount.getText();
        String Description = newLotTADescription.getText();

        try {
            double dubBuyOutPrice = Double.parseDouble(stBuyOutPrice);
            double dubStatingPrice = Double.parseDouble(statingPrice);

            LotTools.wrightLot(new Lot(lotID,userName, newLotName, dubBuyOutPrice, dubStatingPrice, startDate,  endDate, Description));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have made a new lot", ButtonType.OK);
            alert.showAndWait();
            PrimaryController.updatePaneMain("/local/clark/ui/auction/seller/newLot.fxml");

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid inputs retype inputs", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
