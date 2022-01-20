package local.clark.tools;


import local.clark.App;
import local.clark.entries.Lot;
import local.clark.entries.User;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.MessageDigest;

import static local.clark.tools.SpaceTools.getSpace;

public class UserTools {

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND*60;
    public static final long ONE_HOUR = ONE_MINUTE*60;
    public static final long ONE_DAY = ONE_HOUR*24;


    public String hashedPassword (String unhashedPassword){

        String generatedPassword = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(unhashedPassword.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();

            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        }

        catch (Exception e){}

        return generatedPassword;
    }


    public static User checkSpaceUser(User newuser) throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        JavaSpace space = getSpace();
        return (User) space.read(newuser, null, 5000L);
    }


    public static void newSpaceUser(User user){

        JavaSpace space = getSpace();
        //Write
        try {
            space.write(user, null, ONE_DAY * 300);

        } catch (TransactionException | IOException e) {e.printStackTrace();}

    }

    public static void editSelcetedUser(User user){

        try {
            JavaSpace space = getSpace();
            space.write(user, null, 100000 * 31);
            space.take(App.getLotTemp(), null, 5000L);

        } catch (TransactionException | RemoteException | InterruptedException | UnusableEntryException e) {
            e.printStackTrace();
        }
    }


}
