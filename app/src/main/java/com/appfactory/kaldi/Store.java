package com.appfactory.kaldi;

public class Store
{
    public String storeName;
    public Location location;
    public Menu menu;

    /**
     *
     * @param storeName
     * @param location
     * @param menu
     */
    public Store(String storeName, Location location, Menu menu)
    {
        this.storeName = storeName;
        this.location = location;
        this.menu = menu;
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
    public Location getLocation()
    {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public Menu getMenu()
    {
        return menu;
    }

    /**
     *
     * @param menu
     */
    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }
}