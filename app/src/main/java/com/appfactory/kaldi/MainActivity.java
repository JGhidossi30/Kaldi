package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
    private Drinker currentUser;
    private DatabaseReference database;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView emailInput = (TextView) findViewById(R.id.editText8);
        TextView passwordInput = (TextView) findViewById(R.id.editText10);

        Button loginButton1 = findViewById(R.id.loginButton);
        loginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String email = emailInput.getText().toString();
//                String password = passwordInput.getText().toString();
//
//                DrinkerHolder dh = new DrinkerHolder();
//                dh.drinker = new Drinker();
//                Query search = Drinker.database.child("drinkers").orderByChild("email").equalTo(email);
//                search.addValueEventListener(new ValueEventListener()
//                {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        System.out.println("are we here");
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            System.out.println("thsi the shit we sposed to see: " + snapshot.getValue());
//                            //if (snapshot.getValue().equals(email))
//                            if (dataSnapshot.exists())
//                                dh.drinker = new Drinker(snapshot.getValue(Drinker.class).name, snapshot.getValue(Drinker.class).password, snapshot.getValue(Drinker.class).email);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) { }
//                });
//
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("dh.drinker.password is " + dh.drinker.password);
//                if (!dh.drinker.password.equals(password))
//                {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
//                    toast.show();
//                }
//                else if (dh.drinker == null)
//                {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
//                    toast.show();
//                }
//                else if (dh.drinker.email.equals(email) && dh.drinker.password.equals(password))
//                {
//                    currentUser = dh.drinker;
//
//                    //Update Page
//                    Intent myIntent = new Intent(view.getContext(), Register.class);
//                    startActivity(myIntent);
//                }


                Intent myIntent = new Intent(view.getContext(), Drinker_Main.class);
                startActivity(myIntent);
            }
        });
        Button button2 =  (Button) findViewById(R.id.regButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }

        });
    }

    /**
     *
     */
    public static class DrinkerHolder
    {
        Drinker drinker;
    }

    private boolean validateEmail(String email)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return false;
        }
        return true;
    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();
    }
}