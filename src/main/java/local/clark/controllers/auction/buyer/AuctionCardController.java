package local.clark.controllers.auction.buyer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import local.clark.App;
import local.clark.controllers.PrimaryController;
import local.clark.entries.Bid;
import local.clark.entries.Lot;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AuctionCardController {

    @FXML Label auctionCardLBLotID;
    @FXML Label auctionCardLBItemName;
    @FXML Label auctionCardLBLotOwner;
    @FXML Label auctionCardLBTimeLeft;
    @FXML Label auctionCardLBBidAmount;
    @FXML Label auctionCardLBBuyOutPrice;
    @FXML Label auctionCardLBDescription;


    Lot lot;
    public void setLot(Lot lot){this.lot = lot;}


    @FXML public void initialize() {

        try {
            auctionCardLBItemName.setText(lot.itemName);
            auctionCardLBLotOwner.setText(lot.sellerUserName);
            auctionCardLBTimeLeft.setText(lot.timeRemanding() + " days");
            auctionCardLBBidAmount.setText("" + getHighestBid());
            auctionCardLBBuyOutPrice.setText("" + lot.buyoutPrice);
            auctionCardLBDescription.setText(lot.itemDescription);
        }catch (Exception e){

        }

    }

    private double getHighestBid() {

        List<Bid> list = lot.bidList;
        if (list.isEmpty()) {
            return 0;
        }
        Collections.sort(list);
        return list.get(0).bidPrice;

    }

    public void selectLot() throws IOException {
        App.setLotTemp(lot);
        PrimaryController.updatePaneMain("/local/clark/ui/auction/buyer/auctionBuyersPage.fxml");
    }


}
