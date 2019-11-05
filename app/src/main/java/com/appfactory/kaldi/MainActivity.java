package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

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
        Merchant business = new Merchant("admin", "hi", "ghidossi@usc.edu", "Store Name", new Location(0, 0), new Menu());

        Button button1 =  (Button) findViewById(R.id.loginButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Register.class);
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
    @Override
    protected void onStart()
    {
        super.onStart();
    }
}