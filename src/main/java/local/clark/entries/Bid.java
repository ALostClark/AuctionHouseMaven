package local.clark.entries;

import net.jini.core.entry.Entry;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bid implements Entry, Comparable<Bid> {

    public String bidUserName;
    public Double bidPrice;
    public LocalDate bidDate;


    public Bid(){}

    public Bid(String bidUserName, double bidPrice, LocalDate bidDate){
        this.bidUserName = bidUserName;
        this.bidPrice = bidPrice;
        this.bidDate = bidDate;

    }


    @Override
    public int compareTo(Bid o) {
        return Double.compare(bidPrice, o.bidPrice)*-1;
    }


}
