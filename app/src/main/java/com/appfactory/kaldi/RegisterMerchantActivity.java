package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class RegisterMerchantActivity extends AppCompatActivity implements Serializable
{
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__merchant);

        Button registerButton =  (Button) findViewById(R.id.register_student);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                System.out.println("did i get here2");

                TextView storeNameInput = findViewById(R.id.storeInput);
                TextView nameInput = findViewById(R.id.adminInput1);
                TextView emailInput = findViewById(R.id.emailInput);
                TextView passwordInput = findViewById(R.id.passwordInput);
                TextView confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
                TextView addressInput = findViewById(R.id.addressInput);
                TextView initialItemInput = findViewById(R.id.initialItemInput);
                TextView caffeineInput = findViewById(R.id.caffeineInput);

                String storeName = nameInput.getText().toString();
                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();
                String address = addressInput.getText().toString();
                String initialItem = initialItemInput.getText().toString();
                String caffeine = caffeineInput.getText().toString();
                if (!password.equals(confirmPassword))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (Merchant.emailExists(email))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (!validateEmail(email))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    Merchant merchantMan = new Merchant(name, password, email, storeName, address, new Menu(new Item(initialItem, Integer.parseInt(caffeine))));
                    //Update Page
                    Intent myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
                    //myIntent.putExtra("Drinker", merchantMan);
                    startActivity(myIntent);
                }
            }
        });
    }

    /**
     *
     * @param email
     * @return
     */
    private boolean validateEmail(String email)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return false;
        }
        return true;
    }
}
