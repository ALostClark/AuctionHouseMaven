package local.clark.entries;
import net.jini.core.entry.*;

public class User implements Entry{


    public Integer id;
    public String username;
    public String hashedPassword;

    public String phoneNumber;

    public User (){}


    public User (int id, String username, String hashedPassword, String phoneNumber){

        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.phoneNumber = phoneNumber;

    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }


}
