package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ManageStoreActivity extends AppCompatActivity
{
    private Button newItem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__store);
        String userName = getIntent().getStringExtra("currentUser");
        getStores(userName);
    }
    public void getStores(String userName)
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        Log.d("db", "" + database);
//        database.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
//                {
//                    Merchant merchant = postSnapshot.getValue(Merchant.class);
//                    if(merchant.stores != null)
//                    {
//                        for (int i = 0; i < merchant.stores.size(); i++)
//                        {
//                            if (merchant.name == userName)
//                            {
//                                List<String> stores = merchant.stores;
//                                for (String storeName : stores) {
//                                    addStoreItem(storeName);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError)
//            {
//
//            }
//        });
    }
    public void addStoreItem(Item item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name + "             " + item.caffeine + "mg" + "            " + "$" + item.price;
        newItem = new Button(this);
        newItem.setText(itemContent);
        layout.addView(newItem);
        newItem.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

            }
        });
    }
}
