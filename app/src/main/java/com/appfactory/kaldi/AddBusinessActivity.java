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

        TextView businessNameInput = (TextView) findViewById(R.id.storeInput);
        TextView locationInput = (TextView) findViewById(R.id.addressInput);
        TextView initialItemInput = (TextView) findViewById(R.id.initialItemInput);
        TextView caffeineInput = (TextView) findViewById(R.id.caffeineInput);

        String businesName = businessNameInput.getText().toString();
        String location = locationInput.getText().toString();
        String initialItem = initialItemInput.getText().toString();
        String caffeine = caffeineInput.getText().toString();
        Button addBusiness =  (Button) findViewById(R.id.addBusiness);
        addBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
                Query search;
//                if (getIntent().getBooleanExtra("isDrinker", true))
//                    search = database.child("drinkers").orderByKey();
//                else
                    search = database.child("merchants").orderByKey();
                search.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.getKey().equals(getIntent().getStringExtra("currentUser"))) {
                                Merchant merchant;
//                                if (getIntent().getBooleanExtra("isDrinker", true))
//                                    drinker = snapshot.getValue(Drinker.class);
//                                else
                                    merchant = snapshot.getValue(Merchant.class);
                                merchant.id = snapshot.getKey();
                                merchant.stores.add(new Store(businesName, location, new Menu(new Item(initialItem, Integer.parseInt(caffeine)))));
                                merchant.submitToDatabase();

                                //Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class); Might want to bring this page back

//                                if (getIntent().getBooleanExtra("isDrinker", true))
//                                    myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
//                                else
                                Intent myIntent = myIntent = new Intent(getApplicationContext(), MerchantMainActivity.class);
                                String currentUser = getIntent().getStringExtra("currentUser");
                                boolean isDrinker = getIntent().getBooleanExtra("isDrinker", true);
                                myIntent.putExtra("currentUser", currentUser);
                                myIntent.putExtra("isDrinker", isDrinker);
                                startActivityForResult(myIntent, 0);

                                Toast toast = Toast.makeText(getApplicationContext(), "New Business Added!", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                toast.show();
                                break;
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
