package com.appfactory.kaldi;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Drinker
{
    public String name, password, email;
    public List<Trip> tripHistory;
    public List<String> drinkPreferences;
    public Location curLocation;
    public int dailyCaffeine;
    protected  static DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
    protected String id;

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
        this.id = database.push().getKey();
        this.tripHistory = new ArrayList<Trip>();
        this.drinkPreferences = new ArrayList<String>();
        submitToDatabase();
    }

    /**
     *
     * @param name
     * @param password
     * @param email
     * @param id
     */
    public Drinker(String name, String password, String email, String id)
    {
        this(name, password, email);
        this.id = id;
    }

    /**
     *
     */
    public void submitToDatabase()
    {
        database.child("drinkers").child(id).setValue(this);
    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean exists(String email)
    {
        Query search = database.child("drinkers").orderByChild("email").equalTo(email);
        System.out.println(search.toString());
//        search.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                int i = 0;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    i++;
//                }
//                System.out.println("--------------------                 Num: " + i);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) { }
//        });
        return true;
    }

    /**
     *
     */
    public void addTrip(Trip t)
    {
        tripHistory.add(t);
    }

    /**
     *
     */
    public void addDrink(Item i)
    {
        drinkPreferences.add(i.toString());
    }

    /**
     *
     */
    public void openMap(Drinker u)
    {

    }
}