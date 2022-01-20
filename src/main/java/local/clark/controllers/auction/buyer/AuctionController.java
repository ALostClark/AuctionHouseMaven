package local.clark.controllers.auction.buyer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import local.clark.App;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.io.IOException;


public class AuctionController {

    @FXML VBox vBoxAvailableAuctions;

    @FXML public void initialize() throws IOException {

        App.setLotTemp(null);
        update();

    }

    public void update() throws IOException {

        vBoxAvailableAuctions.getChildren().clear();

        for (Lot lot : LotTools.lotsList()) {

            if (!lot.sellerUserName.equals(App.getUser().username) && lot.active && lot.timeRemanding() >= 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/local/clark/ui/auction/buyer/auctionCard.fxml"));

                AuctionCardController auctionCardController = new AuctionCardController();
                loader.setController(auctionCardController);

                auctionCardController.setLot(lot);

                vBoxAvailableAuctions.getChildren().add(loader.load());
            }

        }
    }

}
