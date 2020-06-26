package com.example.afinal;
import java.io.Serializable;

public class UserProfile implements Serializable {
    private String userfullname;
    private String username;
    private String usermail;
    private String userpassword;
    public static int userid =0;
    public UserProfile(){

    }


    public UserProfile(String userfullname, String username, String usermail, String userpassword, String usernumber) {
        this.userfullname = userfullname;
        this.username = username;
        this.usermail = usermail;
        this.userpassword = userpassword;
        this.usernumber = usernumber;
    }
    public String getUserfullname() {
        return userfullname;
    }
    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    private String usernumber;
}