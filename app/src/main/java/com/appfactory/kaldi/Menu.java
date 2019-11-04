package com.appfactory.kaldi;

import java.util.ArrayList;

public class Menu
{
    public ArrayList<Item> menu;

    /**
     *
     * @param i
     */
    public void addItem(Item i)
    {
        //Add only one item
        menu.add(i);
    }

    /**
     *
     */
    public void displayMenu()
    {
        for (Item i : menu)
        {
            System.out.println(i.name);
        }
    }

    /**
     *
     * @param i
     */
    public void removeItem(Item i)
    {
        //remove item if exists
    }

    /**
     *
     * @param i
     */
    public void editItem(Item i)
    {

    }
}