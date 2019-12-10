package com.appfactory.kaldi;

import java.util.ArrayList;
import java.util.List;

public class Store
{
    public String storeName, location;
    public boolean validated;
    public List<Item> menu;
    public List<Order> orderHistory;

    /**
     *
     */
    public Store()
    {
        this.menu = new ArrayList<Item>();
        this.orderHistory = new ArrayList<Order>();
    }

    /**
     *
     * @param storeName
     * @param location
     */
    public Store(String storeName, String location)
    {
        this.storeName = storeName;
        this.location = location;
        this.validated = false;
        this.menu = new ArrayList<Item>();
        this.orderHistory = new ArrayList<Order>();
    }

    /**
     *
     * @return
     */
    public String getStoreName()
    {
        return storeName;
    }

    /**
     *
     * @param storeName
     */
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    /**
     *
     * @return
     */
    public String getLocation()
    {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public List<Item> getMenu()
    {
        return menu;
    }

    /**
     *
     * @param menu
     */
    public void setMenu(List<Item> menu)
    {
        this.menu = menu;
    }

    /**
     *
     * @return
     */
    public List<Order> getOrderHistory()
    {
        return orderHistory;
    }

    /**
     *
     * @param orderHistory
     */
    public void setOrderHistory(List<Order> orderHistory)
    {
        this.orderHistory = orderHistory;
    }
}