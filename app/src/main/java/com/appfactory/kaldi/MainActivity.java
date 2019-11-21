package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable
{
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView emailInput = (TextView) findViewById(R.id.editText8);
        TextView passwordInput = (TextView) findViewById(R.id.editText10);

        Button loginButton1 = findViewById(R.id.loginButton);
        loginButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                Query search = Drinker.database.child("drinkers").orderByChild("email").equalTo(email);
                search.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        System.out.println("start on data change");
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
                        else if (dataSnapshot.getChildrenCount() == 0)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Email does not match our records!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                        else
                        {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                if (snapshot.exists())
                                {
                                    Drinker drinker = snapshot.getValue(Drinker.class);
                                    if (!drinker.password.equals(password)) {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    } else if (drinker.email.equals(email) && drinker.password.equals(password)) {
                                        //Update Page
                                        Intent myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
                                        myIntent.putExtra("Drinker", drinker);
                                        startActivity(myIntent);
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
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });
        Button button2 =  (Button) findViewById(R.id.regButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                //myIntent.putExtra("Drinker", (Seria);

                startActivity(myIntent);
            }

        });
    }

    private boolean validateEmail(String email)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return false;
        }
        return true;
    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();
    }
}