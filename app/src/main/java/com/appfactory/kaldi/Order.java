package com.appfactory.kaldi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    public List<Item> items;
    public LocalTime time;

    public Order()
    {
        items = new ArrayList<Item>();
        time = LocalTime.now();
    }

    public List<Item> getItems()
{
    return this.items;
}

    public LocalTime getTime()
    {
        return this.time;
    }
}