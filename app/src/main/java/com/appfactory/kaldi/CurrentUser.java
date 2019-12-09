package com.appfactory.kaldi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private HashMap<String, Order> cart;
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
            this.cart = currentUser.cart;
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
            this.cart = currentUser.cart;
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
    public String getId() { return this.id; }
    public int getDailyCaffeine() {
        return this.dailyCaffeine;
    }
    public List<Order> getOrderHistory() {
        return this.orderHistory;
    }
    public HashMap<String, Order> getCart() { return this.cart; }
    public List<Store> getStores() {
        return this.stores;
    }

    public void setNullDrinkerMerchant(int nullDrinkerMerchant) {
        if (nullDrinkerMerchant > 0 && nullDrinkerMerchant < 3)
            this.nullDrinkerMerchant = nullDrinkerMerchant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDailyCaffeine(int dailyCaffeine) {
        this.dailyCaffeine = dailyCaffeine;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setCart(HashMap<String, Order> cart) {
        this.cart = cart;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

//    /**
//     *
//     * @return
//     */
//    public boolean checkout()
//    {
//        if (cart.size() == 0)
//            return false;
//        Order newOrder = new Order();
//        Iterator cartIterator = cart.entrySet().iterator();
//        while (cartIterator.hasNext())
//        {
//           //add items in cart to newOrder
//        }
//        newOrder.setTime(LocalTime.now());
//        orderHistory.add(newOrder);
//
//        cart = new HashMap<String, Order>();
//        return true;
//    }
}
