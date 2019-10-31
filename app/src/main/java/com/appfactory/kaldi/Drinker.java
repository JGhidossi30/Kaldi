package com.appfactory.kaldi;

import java.util.ArrayList;
import android.location.Location;

public class Drinker extends User
{
    public ArrayList<Trip> tripHistory;
    public ArrayList<String> drinkPreferences;
    public Location curLocation;
    public int dailyCaffeine;

    /**
     * Default constructor required for calls to DataSnapshot.getValue(Drinker.class)
     */
    public Drinker(String id, String fullname, String username, String password)
    {
        super(id, fullname, username, password);
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
    public void openMap(User u)
    {

    }
}