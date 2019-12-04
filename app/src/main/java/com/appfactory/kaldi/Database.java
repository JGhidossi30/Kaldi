package com.appfactory.kaldi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Database
{
    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public List<Merchant> getMerchants()
    {
        lock.lock();
        try
        {
            count++;
        }
        finally
        {
            lock.unlock();
        }
        return new ArrayList<Merchant>();
    }

    public void updateDatabase()
    {

    }
}