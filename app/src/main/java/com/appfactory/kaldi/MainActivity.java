package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
    private Drinker currentUser;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button1 =  (Button) findViewById(R.id.loginButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }
        });
        Button button2 =  (Button) findViewById(R.id.regButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }

        });

        //Connect to Database
//        writeNewUser("merchants", "Jimmy", "what", "kyle");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

//        database.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    Drinker u = snapshot.getValue(Drinker.class);
//                    System.out.println("------------------    " + u.name + "    -------------");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError)
//            {
//
//            }
//        });
    }

    /**
     *
     *
     * @param fullname
     * @param username
     * @param password
     */
//    private void writeNewUser(String userType, String fullname, String username, String password)
//    {
//        this.database = FirebaseDatabase.getInstance().getReference("users");
//
//        String id = this.database.push().getKey();
//        Drinker user;
//        if (userType.equals("drinkers"))
//            user = new Drinker(id, fullname, username, password);
//        else
//            user = new Merchant(id, fullname, username, password);
//        this.database.child(userType).child(id).setValue(user);
//        this.currentUser = user;
//    }
}