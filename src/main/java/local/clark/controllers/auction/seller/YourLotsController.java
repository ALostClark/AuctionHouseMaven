package local.clark.controllers.auction.seller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import local.clark.App;
import local.clark.controllers.PrimaryController;
import local.clark.controllers.auction.buyer.AuctionCardController;
import local.clark.entries.Lot;
import local.clark.tools.LotTools;

import java.io.IOException;

public class YourLotsController {


    @FXML VBox vBoxAvailableAuctions;

    @FXML public void initialize() throws IOException {
        App.setLotTemp(null);
        update();
    }

    public void update() throws IOException {

        for (Lot lot : LotTools.lotsList()) {

            if (lot.sellerUserName.equals(App.getUser().username)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/local/clark/ui/auction/seller/sellerAuctionCard.fxml"));

                SellerAuctionCardController sellerAuctionCardController = new SellerAuctionCardController();
                loader.setController(sellerAuctionCardController);

                sellerAuctionCardController.setLot(lot);

                vBoxAvailableAuctions.getChildren().add(loader.load());
            }
        }

    }

    public void viewBids() throws IOException {

        if (App.getLotTemp() != null){

            PrimaryController.updatePaneMain("/local/clark/ui/auction/seller/viewBids.fxml");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You need Need to select one of your lots", ButtonType.OK);
            alert.showAndWait();
        }

    }
    public void editLot() throws IOException {

        if (App.getLotTemp() != null){
            PrimaryController.updatePaneMain("/local/clark/ui/auction/seller/editLot.fxml");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You need Need to select one of your lots", ButtonType.OK   );
            alert.showAndWait();
        }

    }







}
