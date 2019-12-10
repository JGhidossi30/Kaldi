package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MerchantMainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant__main);

        Button manageStore =  (Button) findViewById(R.id.manageStore);
        manageStore.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), ManageStoreActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button drinkerProfile =  (Button) findViewById(R.id.drinkerProfile);
        drinkerProfile.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button addStore =  (Button) findViewById(R.id.addStore);
        addStore.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), AddBusinessActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button removeStore = (Button) findViewById(R.id.removeStore);
        removeStore.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), RemoveBusinessActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button signOut =  (Button) findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                CurrentUser.getInstance().signOut();
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        CurrentUser.getInstance().signOut();
        Intent myIntent = new Intent(MerchantMainActivity.this, MainActivity.class);
        startActivityForResult(myIntent, 0);
    }
}
