package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MerchantMenuActivity extends AppCompatActivity
{
    private Button newItem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_menu);
        String businessTitle = getIntent().getStringExtra("businessTitle");
        getMenu(businessTitle);
    }

    public void getMenu(String businessTitle)
    {
        Log.d("test" , "" + businessTitle);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Merchant merchant = postSnapshot.getValue(Merchant.class);
                    if((merchant != null) && (merchant.stores != null))
                    {
                        for (int i = 0; i < merchant.stores.size(); i++)
                        {
                            if (merchant.stores.get(i).storeName.equals(businessTitle))
                            {
                                if (merchant.stores.get(i).menu.size() != 0)
                                {
                                    List<Item> menu = merchant.stores.get(i).menu;
                                    for (Item item : menu)
                                    {
                                        addMenuItem(item);
                                    }
                                }
                                else
                                {
                                    Toast toast = Toast.makeText(getApplicationContext(), merchant.stores.get(i).storeName + " has no menu!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();

                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addMenuItem(Item item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name + "             " + item.caffeine + "mg" + "            $" + item.price;
        newItem = new Button(this);
        newItem.setText(itemContent);
        newItem.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(newItem);
    }
}