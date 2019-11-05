package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity
{
    /**
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button1 =  (Button) findViewById(R.id.loginButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Register_Student.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}
