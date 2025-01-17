package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddBusinessActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        String userName = CurrentUser.getInstance().getId();
        TextView businessNameInput = (TextView) findViewById(R.id.storeInput);
        TextView locationInput = (TextView) findViewById(R.id.addressInput);
        Button addBusiness =  (Button) findViewById(R.id.addBusiness);
        addBusiness.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String businessName = businessNameInput.getText().toString();
                String location = locationInput.getText().toString();
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
                Query search = database.child("merchants").orderByKey().equalTo(userName);
                search.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            if ((snapshot.getKey() != null) && (snapshot.getKey().equals(userName)))
                            {
                                Merchant merchant = snapshot.getValue(Merchant.class);
                                if(merchant != null)
                                {
                                    merchant.id = snapshot.getKey();
                                    merchant.stores.add(new Store(businessName, location));
                                    merchant.submitToDatabase();
                                    Intent myIntent = new Intent(getApplicationContext(), MerchantMainActivity.class);
                                    startActivityForResult(myIntent, 0);
                                    Toast toast = Toast.makeText(getApplicationContext(), "New Business Added!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
}
