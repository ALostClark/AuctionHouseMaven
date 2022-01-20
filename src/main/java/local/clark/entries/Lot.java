package local.clark.entries;

import net.jini.core.entry.Entry;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Lot implements Entry {

    public String lotID;
    public String sellerUserName;
    public String itemName;
    public String itemDescription;

    public LocalDate startDate;
    public LocalDate endDate;

    public List <Bid> bidList;

    public Double buyoutPrice;
    public Double statingPrice;


    public Boolean active;

    public Lot(){}

    public Lot(String lotID, String sellerUserName, String itemName, double buyoutPrice, double statingPrice, LocalDate startDate, LocalDate endDate, String itemDescription){

        this.lotID = lotID;
        this.sellerUserName = sellerUserName;
        this.itemName = itemName;

        this.startDate = startDate;
        this.endDate = endDate;

        this.buyoutPrice = buyoutPrice;
        this.statingPrice = statingPrice;

        this.itemDescription = itemDescription;

        active = true;

        bidList = new ArrayList<>();

    }


    public int amountBids(){
        return bidList.size();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public void newEndDate(LocalDate endDate){
        this.endDate = endDate;
    }


    public void newBid(Bid bid){
        bidList.add(bid);
    }

    public double highestBid(){
        if (bidList.isEmpty()) {
            return statingPrice;
        }
        Collections.sort(bidList);
        return bidList.get(0).bidPrice;
    }

    public int timeRemanding(){
        LocalDate localDate = LocalDate.now();
        return (int)DAYS.between(localDate, endDate);

    }


}
