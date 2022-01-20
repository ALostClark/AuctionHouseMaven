package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import local.clark.entries.Bid;
import local.clark.entries.Lot;

public class BidController {


    Bid bid;

    @FXML Label bidderUsername;
    @FXML Label bidderBidAmount;
    @FXML Label bidderBidDate;
    @FXML Label BidderWinner;




    public void setBid(Bid bid){

        this.bid = bid;

    }


    @FXML
    public void initialize() {

        try{

            bidderUsername.setText(bid.bidUserName);
            bidderBidAmount.setText("" + bid.bidPrice);
            bidderBidDate.setText("" + bid.bidDate);
            BidderWinner.setText("");

        }catch (Exception e){

        }


    }




}
