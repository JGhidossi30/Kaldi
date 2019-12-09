package com.appfactory.kaldi;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Drinker
{
    public List<Store> stores;

    /**
     * Default constructor
     */
    public Merchant()
    {

    }

    /**
     * Constructor
     */
    public Merchant(String name, String password, String email, String storeName, String location)
    {
        super(name, password, email);
        this.stores = new ArrayList<Store>();
        this.stores.add(new Store(storeName, location));
        submitToDatabase();
    }

    /**
     *
     * @param name
     * @param password
     * @param email
     * @param storeName
     * @param location
     * @param menu
     * @param id
     */
    public Merchant(String name, String password, String email, String storeName, String location, List<Item> menu, String id)
    {
        super(name, password, email);
        this.stores = new ArrayList<Store>();
        this.stores.add(new Store(storeName, location));
        this.id = id;
        submitToDatabase();
    }

    /**
     * 
     */
    @Override
    public void submitToDatabase()
    {
        this.database.child("merchants").child(id).setValue(this);
    }

    /**
     *
     */
    public void addstore(Store store)
    {
        stores.add(store);
    }
}