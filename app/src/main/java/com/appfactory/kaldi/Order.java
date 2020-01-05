package com.appfactory.kaldi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    public String storeName;
    public List<Item> items;
    public String time;

    public Order() { items = new ArrayList<Item>(); }

    public Order(String storeName, List<Item> items, String time)
    {
        this.storeName = storeName;
        this.items = items;
        this.time = time;
    }

    /**
     *
     * @return
     */
    public String getStoreName() { return this.storeName; }

    /**
     *
     * @param storeName
     */
    public void setStoreName(String storeName) { this.storeName = storeName; }

    /**
     *
     * @return
     */
    public List<Item> getItems() { return this.items; }

    /**
     *
     * @param items
     */
    public void setItems(List<Item> items) { this.items = items; }

    /**
     *
     * @return
     */
    public String getTime() { return this.time; }

    /**
     *
     * @param time
     */
    public void setTime(String time) { this.time = time; }
}