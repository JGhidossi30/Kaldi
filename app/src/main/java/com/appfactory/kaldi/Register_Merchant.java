package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                String storeName = findViewById(R.id.storeInput).toString();
                String nameInput = findViewById(R.id.adminInput1).toString();
                String emailInput = findViewById(R.id.emailInput).toString();
                String passwordInput = findViewById(R.id.passwordInput).toString();
                String confirmPasswordInput = findViewById(R.id.confirmPasswordInput).toString();
                //check for  email and password (these two are done for student already)
                //deal with storename after
                //create page following registration of merchant for address, then add page
                //where the merchant can add to its menu, this can be the merchant submitting via text
                // after this, store in DB and finish registration, following the clicking of
                //"finish registration" we will go to their own storefront page

                Merchant business = new Merchant(nameInput, passwordInput, emailInput, "Store Name", new Location(0, 0), new Menu());

//                we need to now go to a new page once registered
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }

        });
    }

}
