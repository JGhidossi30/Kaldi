package com.appfactory.kaldi;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    public List<Item> items;

    public Order(ArrayList<Item> items)
    {
        items = items;
    }
}