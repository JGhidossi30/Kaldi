package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable
{
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView emailInput = (TextView)findViewById(R.id.email);
                TextView passwordInput = (TextView)findViewById(R.id.password);
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                if (!validateEmail(email))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (password.isEmpty())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Password is empty!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
                    int radioButtonId = radioGroup.getCheckedRadioButtonId();
                    if (radioButtonId != -1)
                    {
                        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonId);
                        if (radioButton != null);
                        {
                            int id = (Integer.parseInt((String) radioButton.getTag()));
                            DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
                            Query search;
                            if (id == 1)
                            {
                                search = database.child("drinkers").orderByChild("email").equalTo(email);
                            }
                            else {
                                search = database.child("merchants").orderByChild("email").equalTo(email);
                            }
                            search.addListenerForSingleValueEvent(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                {
                                    if (id == 1)
                                    {
                                        if(dataSnapshot.getValue() !=  null)
                                        {
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                Drinker drinker = snapshot.getValue(Drinker.class);
                                                if ((drinker != null) && (drinker.email.equals(email))) {
                                                    if (!drinker.password.equals(password)) {
                                                        Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
                                                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                                        toast.show();
                                                        return;
                                                    } else {
                                                        drinker.setId(snapshot.getKey());
                                                        CurrentUser.getInstance().signIn(drinker);
                                                        Intent intent = new Intent(view.getContext(), DrinkerMainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            }
                                        }
                                        else
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                            toast.show();
                                        }
                                    }
                                    else
                                    {
                                        if(dataSnapshot.getValue() !=  null)
                                        {
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                Merchant merchant = snapshot.getValue(Merchant.class);
                                                if ((merchant != null) && (merchant.email.equals(email)))
                                                {
                                                    if (!merchant.password.equals(password)) {
                                                        Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
                                                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                                        toast.show();
                                                    } else {
                                                        merchant.setId(snapshot.getKey());
                                                        CurrentUser.getInstance().signIn(merchant);
                                                        Intent intent = new Intent(view.getContext(), MerchantMainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            }
                                        }
                                        else
                                        {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                            toast.show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) { Log.d("onCancelled:", databaseError.toString()); }
                            });
                        }
                    }
                }
            }
        });
        Button button2 = (Button)findViewById(R.id.regButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivity(myIntent);
            }

        });
    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();
    }

    /**
     * Validates that the input given is in the correct format.
     *
     * @param email
     * @return True: Email is valid. False: Email is not valid.
     */
    public static boolean validateEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
