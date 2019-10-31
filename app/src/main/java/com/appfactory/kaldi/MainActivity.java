package com.appfactory.kaldi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to Database
        writeNewUser("users", "John Doe", "johnnyd", "heynow");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    User u = snapshot.getValue(User.class);
                    System.out.println("------------------    " + u.fullname + "    -------------");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     *
     *
     * @param fullname
     * @param username
     * @param password
     */
    private void writeNewUser(String userType, String fullname, String username, String password)
    {
        this.database = FirebaseDatabase.getInstance().getReference(userType);

        String id = this.database.push().getKey();
        User user = new User(fullname, username, password);
        this.database.child(id).setValue(user); //userid
    }
}