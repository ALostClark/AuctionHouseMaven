package local.clark.controllers.auction.buyer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import local.clark.App;
import local.clark.controllers.PrimaryController;
import local.clark.entries.Bid;
import local.clark.entries.Lot;
import local.clark.entries.User;
import local.clark.tools.UserTools;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static local.clark.tools.SpaceTools.getSpace;

public class AuctionBuyersPageController {


    @FXML TextField auctionTFBidAmount;

    @FXML Label auctionCardLBLotID;
    @FXML Label auctionCardLBItemName;
    @FXML Label auctionCardLBLotOwner;
    @FXML Label auctionCardLBTimeLeft;
    @FXML Label auctionCardLBBidAmount;
    @FXML Label auctionCardLBBuyOutPrice;
    @FXML Label auctionCardLBDescription;


    public void initialize() {

       update();

    }

    public void update(){

        try {
            auctionCardLBLotID.setText(App.getLotTemp().lotID);
            auctionCardLBItemName.setText(App.getLotTemp().itemName);
            auctionCardLBLotOwner.setText(App.getLotTemp().sellerUserName);
            auctionCardLBTimeLeft.setText(App.getLotTemp().timeRemanding() + " days");
            auctionCardLBBidAmount.setText("" + getHighestBid());
            auctionCardLBBuyOutPrice.setText("" + App.getLotTemp().buyoutPrice);
            auctionCardLBDescription.setText(App.getLotTemp().itemDescription);
        }catch (Exception e){

        }
    }

    private double getHighestBid() {
        List<Bid> bids = App.getLotTemp().bidList;
        if (bids.isEmpty()) { return 0; }
        Collections.sort(bids);
        return bids.get(0).bidPrice;

    }

    public void makeBid() {

        try{
            placeBid();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your bid has been placed", ButtonType.OK);
            alert.showAndWait();

            PrimaryController.updatePaneMain("/local/clark/ui/auction/buyer/auction.fxml");



        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "In valid bid", ButtonType.CANCEL);
            alert.showAndWait();

        }

    }

    public void placeBid() throws IOException {

        String strBidAmount = auctionTFBidAmount.getText();
        double doubBidAmount = Double.parseDouble(strBidAmount);

        LocalDate localDate = LocalDate.now();

        try {
            if (App.getLotTemp().buyoutPrice > doubBidAmount && App.getLotTemp().highestBid() > doubBidAmount) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You need do a higher bid", ButtonType.OK);
                alert.showAndWait();
                update();
            }else {

                Bid bid = new Bid(App.getUser().toString(), doubBidAmount, localDate);
                Lot template = new Lot();

                template.itemName = App.getLotTemp().itemName;

                Lot lot = (Lot) getSpace().take(template, null, 5000L);
                lot.newBid(bid);

                getSpace().write(lot, null, 1000 * 60 * 30L);
            }

        }catch (Exception e){

        }

    }


    public void buyout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to make buy this item ", ButtonType.YES , ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            User tepUser = new User();
            tepUser.username = App.getLotTemp().sellerUserName;

            try {
                User qI = UserTools.checkSpaceUser(tepUser);
                Alert userInfo = new Alert(Alert.AlertType.CONFIRMATION, "The seller contact if fo is " +  qI.phoneNumber , ButtonType.YES , ButtonType.CANCEL);

                userInfo.showAndWait();
            } catch (TransactionException | UnusableEntryException | RemoteException | InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

}
