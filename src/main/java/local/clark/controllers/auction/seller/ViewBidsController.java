package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import local.clark.App;
import local.clark.entries.Bid;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.io.IOException;

public class ViewBidsController {


    @FXML VBox vBoxBidList;

    @FXML public void initialize() {

        try {
            System.out.println("" + App.getLotTemp().bidList.size());
            update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() throws IOException {

        vBoxBidList.getChildren().clear();

        for (Bid bids : App.getLotTemp().bidList) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/local/clark/ui/auction/seller/bids.fxml"));

            BidController bidController = new BidController();
            loader.setController(bidController);
            bidController.setBid(bids);

            vBoxBidList.getChildren().add(loader.load());
        }

    }

}
