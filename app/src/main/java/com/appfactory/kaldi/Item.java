package com.appfactory.kaldi;

import java.util.Random;

public class
Item
{

    public String name;
    public int caffeine;
    public double price;

    public Item() { }

    /**
     *
     */
    public Item(String name, int caffeine, double price)
    {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        this.name = name;
        this.caffeine = caffeine;
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     *
     * @return
     */
    public int getCaffeine()
    {
        return this.caffeine;
    }

    /**
     *
     * @return
     */
    public double getPrice() { return this.price; }
}
