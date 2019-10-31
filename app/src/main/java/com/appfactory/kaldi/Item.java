package com.appfactory.kaldi;

import java.nio.charset.Charset;
import java.util.Random;

public class Item
{

    public String id, name;
    public int caffeine;

    /**
     *
     */
    public Item(String name, int caffeine)
    {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        this.id = new String(array, Charset.forName("UTF-8"));
        this.name = name;
        this.caffeine = caffeine;
    }

    /**
     *
     */
    public String getId()
    {
        return this.id;
    }

    /**
     *
     */
    public String getName()
    {
        return this.name;
    }

    /**
     *
     */
    public int getCaffeine()
    {
        return this.caffeine;
    }
}
