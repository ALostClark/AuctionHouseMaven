package local.clark.tools;

import local.clark.App;
import local.clark.entries.Bid;
import local.clark.entries.Lot;
import local.clark.entries.User;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import net.jini.space.JavaSpace05;
import net.jini.space.MatchSet;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static local.clark.tools.SpaceTools.getSpace;

public class LotTools {

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND*60;
    public static final long ONE_HOUR = ONE_MINUTE*60;
    public static final long ONE_DAY = ONE_HOUR*24;

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    public static List<Lot> lotsList(){

        JavaSpace05 space05 = (JavaSpace05) getSpace();

        Collection<Entry> templates = new ArrayList<>();
        templates.add(new Lot());

        Lot template = new Lot();

        List<Lot> list = new ArrayList<>();
        templates.add(template);
        try {
            MatchSet res = space05.contents(templates, null, ONE_SECOND*5, 10);
            Lot lotTemp = (Lot) res.next();

            while (lotTemp != null){
                list.add(lotTemp);
                lotTemp = (Lot) res.next();
            }
            Collections.reverse(list);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static double getHighestBid(Lot lot) {
        List<Bid> list = lot.bidList;
        if (list.isEmpty()) {
            return 0;
        }
        Collections.sort(list);
        return list.get(0).bidPrice;

    }



    public Boolean winner(Bid bid){

        LocalDate localDate = LocalDate.now();
        if (App.getLotTemp().endDate.compareTo(bid.bidDate) >= 0 ){
            return true;
        }else{
            return false;
        }
    }

    public static void editSelcetedLot(Lot lot){

        try {
            JavaSpace space = getSpace();
            space.write(lot, null, 100000 * 31);
            space.take(App.getLotTemp(), null, 5000L);

        } catch (TransactionException | RemoteException | InterruptedException | UnusableEntryException e) {
            e.printStackTrace();
        }
    }


    public static void removeLot(Lot lot) throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        JavaSpace space = getSpace();
        space.take(lot, null, 5000L);
    }


    public static Lot checkSpaceLot(Lot lot) throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        JavaSpace space = getSpace();
        return (Lot) space.read(lot, null, 5000L);
    }


    public static void wrightLot(Lot lot){

        JavaSpace space = getSpace();
        try {
            space.write(lot, null, ONE_DAY * 31);

        } catch (TransactionException | IOException e) {e.printStackTrace();}
    }



}
