package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ManageProfileActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__profile);
        Drinker d = (Drinker) getIntent().getSerializableExtra("Drinker");

        TextView nameText = (TextView) findViewById(R.id.Name);
        nameText.setText(d.name);

        TextView emailText = (TextView) findViewById(R.id.email);
        emailText.setText(d.email);
    }
}
