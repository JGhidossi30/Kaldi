package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity
{
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button1 =  (Button) findViewById(R.id.studButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), RegisterDrinkerActivity.class);
                startActivity(myIntent);
            }

        });

        Button button2 =  (Button) findViewById(R.id.merchButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), RegisterMerchantActivity.class);

                startActivity(myIntent);
            }

        });
    }
}
