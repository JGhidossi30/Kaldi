package com.appfactory.kaldi;

import android.location.Location;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{
    public String id, fullname, username, password;

    /**
     * Default constructor required for calls to DataSnapshot.getValue(User.class)
     */
    public User() { }

    /**
     *
     *
     * @param fullname
     * @param username
     * @param password
     */
    public User(String id, String fullname, String username, String password)
    {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }
}