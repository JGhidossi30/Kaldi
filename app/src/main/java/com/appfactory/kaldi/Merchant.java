package com.appfactory.kaldi;

public class Merchant extends Drinker
{
    public Menu menu;

    /**
     * Constructor
     */
    public Merchant(String username, String password, String email_address)
    {
        super(username, password, email_address);

    }

    /**
     * 
     */
    @Override
    public void submitToDatabase()
    {
        this.database.child("merchants").child(this.id).setValue(this);
    }
}