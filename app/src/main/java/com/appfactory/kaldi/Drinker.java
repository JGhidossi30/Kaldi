package com.appfactory.kaldi;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@IgnoreExtraProperties
public class Drinker implements Serializable
{
    public String name, password, email;
    public int dailyCaffeine;
    public List<Order> orderHistory;
    public HashMap<String, Order> cart;
    protected static DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
    protected String id;


    /**
     *
     */
    public Drinker()
    {
        this.orderHistory = new ArrayList<Order>();
        this.cart = new HashMap<String, Order>();
    }

    /**
     *
     *
     * @param name
     * @param password
     * @param email
     */
    public Drinker(String name, String password, String email)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.orderHistory = new ArrayList<Order>();
        this.cart = new HashMap<String, Order>();
        this.id = database.push().getKey();
    }

//    /**
//     *
//     * @param name
//     * @param password
//     * @param email
//     * @param id
//     */
//    public Drinker(String name, String password, String email, String id)
//    {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.orderHistory = new ArrayList<Order>();
//        this.id = id;
//    }

    /**
     *
     */
    public void submitToDatabase()
    {
        database.child("drinkers").child(id).setValue(this);
    }

    /**
     *
     */
    public void setId(String id)
    {
        this.id = id;
    }

//    /**
//     *
//     * @return
//     */
//    public boolean checkout()
//    {
//        if (cart.size() == 0)
//            return false;
//        Order newOrder = new Order(cart, LocalTime.now());
//
//        orderHistory.add(newOrder);
//
//        cart = new ArrayList<Item>();
//        return true;
//    }/**
//     *
//     * @return
//     */
//    public boolean checkout()
//    {
//        if (cart.size() == 0)
//            return false;
//        Order newOrder = new Order(cart, LocalTime.now());
//
//        orderHistory.add(newOrder);
//
//        cart = new ArrayList<Item>();
//        return true;
//    }
}