package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import local.clark.App;
import local.clark.controllers.PrimaryController;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.io.IOException;
import java.time.LocalDate;

public class EditLotController {


    @FXML CheckBox editLotTickBoxBuyOut;

    @FXML TextField editLotTFBuyOutPrice;
    @FXML TextField editLotTFItemName;
    @FXML TextField editLotTFStartingAmount;

    @FXML Label editLotLBUserName;
    @FXML Label editLotLotID;

    @FXML DatePicker editLotDatePickerEndDate;

    @FXML TextArea editLotTADescription;

    @FXML ComboBox editLotMenuActiveComboBox;




    private MenuItem menuItemYes = new MenuItem("Yes");
    private MenuItem menuItemNo = new MenuItem("No");

    public void checkBoxUpdate(){
        if (editLotTickBoxBuyOut.isSelected()){
            editLotTFBuyOutPrice.setVisible(true);
        }else{
            editLotTFBuyOutPrice.setVisible(false);
        }
    }


    @FXML public void initialize(){

        editLotLBUserName.setText(App.getUser().username);
        editLotLotID.setText(App.getLotTemp().lotID);

        editLotDatePickerEndDate.setValue(App.getLotTemp().endDate);
        editLotTADescription.setText(App.getLotTemp().itemDescription);
        editLotTFItemName.setText(App.getLotTemp().itemName);
        editLotTFStartingAmount.setText("" + App.getLotTemp().statingPrice);

        checkBoxUpdate();
        editLotTFBuyOutPrice.setText("" + App.getLotTemp().buyoutPrice);

        editLotMenuActiveComboBox.getItems().addAll("Yes", "No");


    }

    public void editLot(){


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to edit this lot", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            String userName = App.getUser().username;

            String newLotName = editLotTFItemName.getText();
            LocalDate endDate = editLotDatePickerEndDate.getValue();
            LocalDate startDate = LocalDate.now();

            String stBuyOutPrice = editLotTFBuyOutPrice.getText();

            String statingPrice = editLotTFStartingAmount.getText();

            String Description = editLotTADescription.getText();

            try {
                double dubBuyOutPrice = Double.parseDouble(stBuyOutPrice);
                double dubStatingPrice = Double.parseDouble(statingPrice);

                Lot lot = new Lot(App.getLotTemp().lotID, userName, newLotName, dubBuyOutPrice, dubStatingPrice, startDate, endDate, Description);

                System.out.println(editLotMenuActiveComboBox.getValue().toString());
                if (editLotMenuActiveComboBox.getValue().toString().equals("No")) {
                    lot.setActive(false);
                } else {
                    lot.setActive(true);
                }

                LotTools.editSelcetedLot(lot);


                try {
                    PrimaryController.updatePaneMain("/local/clark/ui/auction/seller/yourLots.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){

                Alert fail = new Alert(Alert.AlertType.ERROR, "Invalid ", ButtonType.OK);
                fail.showAndWait();
            }
        }


    }

}
