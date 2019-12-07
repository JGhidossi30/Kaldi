package com.appfactory.kaldi;

import java.util.List;

class CurrentUser
{
    private static final CurrentUser instance = new CurrentUser();
    private int nullDrinkerMerchant; //Value 0: Signed Out; Value 1: Drinker; Value 2: Merchant

    private String name;
    private String password;
    private String email;
    private String id;
    private int dailyCaffeine;
    private List<Order> orderHistory;

    private List<Store> stores;

    public static CurrentUser getInstance() {
        return instance;
    }

    private CurrentUser()
    {
        signOut();
    }

    public void signIn(Drinker currentUser)
    {
        if (currentUser instanceof Merchant)
        {
            this.nullDrinkerMerchant = 2;

            this.name = currentUser.name;
            this.password = currentUser.password;
            this.email = currentUser.email;
            this.id = currentUser.id;

            this.dailyCaffeine = currentUser.dailyCaffeine;

            this.orderHistory = currentUser.orderHistory;

            this.stores = ((Merchant)currentUser).stores;
        }
        else
        {
            this.nullDrinkerMerchant = 1;

            this.name = currentUser.name;
            this.password = currentUser.password;
            this.email = currentUser.email;
            this.id = currentUser.id;

            this.dailyCaffeine = currentUser.dailyCaffeine;

            this.orderHistory = currentUser.orderHistory;
        }
    }

    public void signOut()
    {
        this.nullDrinkerMerchant = 0;

        this.name = null;
        this.password = null;
        this.email = null;
        this.id = null;

        this.dailyCaffeine = 0;

        this.orderHistory = null;

        this.stores = null;
    }

    public int getNullDrinkerMerchant() { return this.nullDrinkerMerchant; }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() { return this.email; }

    public String getId() {
        return this.id;
    }

    public int getDailyCaffeine() {
        return this.dailyCaffeine;
    }

    public List<Order> getOrderHistory() {
        return this.orderHistory;
    }

    public List<Store> getStores() {
        return this.stores;
    }
}
