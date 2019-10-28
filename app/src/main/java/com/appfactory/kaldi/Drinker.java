package com.appfactory.kaldi;

import java.util.ArrayList;
import android.location.Location;

public class Drinker extends User {
    private ArrayList<Trip> tripHistory;
    private ArrayList<String> drinkPreferences;
    private Location curLocation;
    private int dailyCaffeine;

    public void addTrip(Trip t) {
        tripHistory.add(t);
    }

    public void addDrink(Item i) {
        drinkPreferences.add(i.toString());
    }

    public void openMap(User u) {

    }
}