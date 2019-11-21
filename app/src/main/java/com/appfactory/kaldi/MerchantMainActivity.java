package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MerchantMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}
