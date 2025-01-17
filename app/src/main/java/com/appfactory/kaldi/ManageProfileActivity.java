package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ManageProfileActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__profile);

        TextView currentName = (TextView)findViewById(R.id.currentName);
        currentName.setText("Current Name: " + CurrentUser.getInstance().getName());
        TextView currentEmail = (TextView)findViewById(R.id.currentEmail);
        currentEmail.setText("Current Email: " + CurrentUser.getInstance().getEmail());
        TextView currentPassword = (TextView)findViewById(R.id.currentPassword);
        currentPassword.setText("Current Password: " + CurrentUser.getInstance().getPassword());

        Button checkout = (Button) findViewById(R.id.submit);
        checkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                EditText nameInput = (EditText) findViewById(R.id.name);
                EditText emailInput = (EditText) findViewById(R.id.email);
                EditText passwordInput = (EditText) findViewById(R.id.password);

                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (!name.isEmpty())
                    CurrentUser.getInstance().setName(name);
                if (!email.isEmpty())
                    CurrentUser.getInstance().setEmail(email);
                if (!password.isEmpty())
                    CurrentUser.getInstance().setPassword(password);
                Database.getInstance().updateDatabase();

                Intent myIntent;
                myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
                startActivityForResult(myIntent, 0);

                Toast toast = Toast.makeText(getApplicationContext(), "Profile updated!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
    }
}