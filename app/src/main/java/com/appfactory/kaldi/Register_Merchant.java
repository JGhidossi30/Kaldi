package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Merchant extends AppCompatActivity
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
    }
    public void registration(Bundle saveInstanceState)
    {
        Button registerButton =  (Button) findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                //check for  email and password (these two are done for student already)
                //deal with storename after
                //create page following registration of merchant for address, then add page
                //where the merchant can add to its menu, this can be the merchant submitting via text
                // after this, store in DB and finish registration, following the clicking of
                //"finish registration" we will go to their own storefront page




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
                int caffine = Integer.getInteger(caffeineInput.getText().toString());

                if (!password.equals(confirmPassword))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (Merchant.exists(email))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    new Merchant(name, password, email, storeName, address, new Menu(new Item(initialItem, caffeine)));

                    //Update Page
                    Intent myIntent = new Intent(view.getContext(), Register.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
