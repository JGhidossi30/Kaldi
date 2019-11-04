package com.appfactory.kaldi;

import java.util.ArrayList;
import android.location.Location;

public class Merchant extends Drinker
{
    public String storeName;
    public ArrayList<Store> stores;

    /**
     * Constructor
     */
    public Merchant(String storeName, String name, String password, String email_address)
    {
        super(name, password, email_address);
        this.storeName = storeName;
        submitToDatabase();
    }

    /**
     * 
     */
    @Override
    public void submitToDatabase()
    {
        this.database.child("merchants").child(this.id).setValue(this);
    }

    /**
     *
     */
    public void addstore(Store store)
    {
        stores.add(store);
    }
}