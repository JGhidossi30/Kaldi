package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class RegisterDrinkerActivity extends AppCompatActivity implements Serializable
{
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__student);
        Button registerButton = (Button)findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView nameInput = findViewById(R.id.adminInput1);
                TextView emailInput = findViewById(R.id.emailInput);
                TextView passwordInput = findViewById(R.id.passwordInput);
                TextView confirmPasswordInput = findViewById(R.id.confirmPasswordInput);

                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (!password.equals(confirmPassword))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (Drinker.emailExists(email))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    Drinker drinkMan = new Drinker(name, password, email);

                    //Update Page
                    Intent myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
                    myIntent.putExtra("Drinker", drinkMan);
                    startActivity(myIntent);
                }
            }
        });
    }
}
