package com.appfactory.kaldi;

import android.location.Location;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Drinker
{
    public String email_address, username, password, id;
    public ArrayList<Trip> tripHistory;
    public ArrayList<String> drinkPreferences;
    public Location curLocation;
    public int dailyCaffeine;
    protected DatabaseReference database;

    /**
     *
     *
     * @param email_address
     * @param username
     * @param password
     */
    public Drinker(String username, String password, String email_address)
    {
        this.database = FirebaseDatabase.getInstance().getReference("users");
        this.email_address = email_address;
        this.username = username;
        this.password = password;
        this.id = database.push().getKey();
        submitToDatabase();
    }

    public void submitToDatabase()
    {
       this.database.child("drinkers").child(this.id).setValue(this);
    }

    /**
     *
     */
    public void addTrip(Trip t)
    {
        tripHistory.add(t);
    }

    /**
     *
     */
    public void addDrink(Item i)
    {
        drinkPreferences.add(i.toString());
    }

    /**
     *
     */
    public void openMap(Drinker u)
    {

    }
}