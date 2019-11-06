package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Drinker_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinker__main);

        Button manageProfile =  (Button) findViewById(R.id.manageProfile);
        manageProfile.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Manage_Profile.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button historyButton =  (Button) findViewById(R.id.history);
        historyButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Order_History.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button mapButton =  (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}
