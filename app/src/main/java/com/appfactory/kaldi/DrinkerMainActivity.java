package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;



public class DrinkerMainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinker__main);
        Button manageProfile =  (Button) findViewById(R.id.manageProfile);
        manageProfile.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), ManageProfileActivity.class);
                Drinker d = (Drinker) getIntent().getSerializableExtra("Drinker");
                myIntent.putExtra("Drinker", d);
                startActivityForResult(myIntent, 0);
            }
        });
        Button historyButton =  (Button) findViewById(R.id.history);
        historyButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), OrderHistoryActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }

        });
    }

}
