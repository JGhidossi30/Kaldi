package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_Merchant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__merchant);
    }
    public void registration(Bundle saveInstanceState){


        Button registerButton =  (Button) findViewById(R.id.register_button);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeName = findViewById(R.id.storeInput).toString();
                String nameInput = findViewById(R.id.adminInput1).toString();
                String emailInput = findViewById(R.id.emailInput).toString();
                String passwordInput = findViewById(R.id.passwordInput).toString();
                String confirmPasswordInput = findViewById(R.id.confirmPasswordInput).toString();

                Merchant business = new Merchant(storeName, nameInput, passwordInput, emailInput);

//                we need to now go to a new page once registered
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }

        });
    }

}
