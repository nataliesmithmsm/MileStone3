package com.training.Profiles;

import lombok.ToString;

public class Profile {

    private String ID;
    private String Firstname;
    private String Lastname;

    public Profile() {}

    public Profile(String ID, String Firstname, String Lastname){

        this.ID = ID;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    @Override
    public String toString()
    {
        return getID() + " " + getFirstname() + " " + getLastname();
    }
}
