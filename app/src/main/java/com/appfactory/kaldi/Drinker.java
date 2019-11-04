package com.appfactory.kaldi;

import android.location.Location;
import android.renderscript.Sampler;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Drinker
{
    public String name, password, email_address, id;
    public ArrayList<Trip> tripHistory;
    public ArrayList<String> drinkPreferences;
    public Location curLocation;
    public int dailyCaffeine;
    protected  static DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");

    /**
     *
     *
     * @param name
     * @param password
     * @param email_address
     */
    public Drinker(String name, String password, String email_address)
    {
        this.name = name;
        this.password = password;
        this.email_address = email_address;
        this.id = database.push().getKey();
        submitToDatabase();
    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean exists(String email)
    {
        database.child("drinkers")
        boolean exists = false;
        database.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Drinker drinker = snapshot.getValue(Drinker.class);

                    if (drinker.email_address.equals(email))
                        exists = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
        return exists;

//        for (DataSnapshot snapshot : .get)
//        {
//            Drinker drinker = snapshot.getValue(Drinker.class);
//            if (drinker.email_address.equals(email))
//                return true;
//        }
        return false;
    }

    public void submitToDatabase()
    {
       database.child("drinkers").child(this.id).setValue(this);
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