package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import local.clark.App;
import local.clark.entries.Bid;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class SellerAuctionCardController {


    @FXML Label sellerCardLBItemName;
    @FXML Label sellerCardLBTimeLeft;
    @FXML Label sellerCardLBBidActive;
    @FXML Label sellerCardLBBidAmount;
    @FXML Label sellerCardLBBuyOutPrice;
    @FXML Label sellerCardLBDescription;

    Lot lot;


    @FXML public void initialize() {

        try {

            sellerCardLBItemName.setText(lot.itemName);
            sellerCardLBTimeLeft.setText(lot.timeRemanding() + " days");
            sellerCardLBBidAmount.setText("" + LotTools.getHighestBid(lot));
            sellerCardLBBidActive.setText("" + lot.active);
            sellerCardLBBuyOutPrice.setText("" + lot.buyoutPrice);
            sellerCardLBDescription.setText(lot.itemDescription);
        }catch (Exception e){

        }

    }

    public void setLot(Lot lot){

        this.lot = lot;

    }



    public void selectLot() throws IOException {
        App.setLotTemp(lot);

    }







}
