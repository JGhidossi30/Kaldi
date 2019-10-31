package com.appfactory.kaldi;

public class Merchant extends User
{
    public Menu menu;

    /**
     *
     */
    public Merchant(String id, String fullname, String username, String password)
    {
        super(id, fullname, username, password);
    }
}