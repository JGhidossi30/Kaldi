package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreProfileActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);
        Button menuItem =  (Button) findViewById(R.id.menuItem);
        menuItem.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), AddMenuItemActivity.class);
                String currentUser = getIntent().getStringExtra("currentUser");
                boolean isDrinker = getIntent().getBooleanExtra("isDrinker", true);
                myIntent.putExtra("currentUser", currentUser);
                myIntent.putExtra("isDrinker", isDrinker);
                startActivityForResult(myIntent, 0);
            }
        });
        Button businessLicense =  (Button) findViewById(R.id.businessLicense);
        businessLicense.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                String currentUser = getIntent().getStringExtra("currentUser");
                boolean isDrinker = getIntent().getBooleanExtra("isDrinker", true);
            }
        });
    }

}
