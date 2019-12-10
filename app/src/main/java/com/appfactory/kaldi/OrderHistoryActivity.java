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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity
{
    private Button orderItem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__history);
        displayCart();
    }

    public void displayCart()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        Query search;
        int userType = CurrentUser.getInstance().getNullDrinkerMerchant();
        String userName = CurrentUser.getInstance().getId();
        if (userType == 1)
            search = database.child("drinkers").orderByKey().equalTo(userName);
        else
            search = database.child("merchants").orderByKey().equalTo(userName);
        search.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    if ((snapshot.getKey() != null) && (snapshot.getKey().equals(userName)))
                    {
                        Drinker drinker;
                        if (userType == 1)
                        {
                            drinker = snapshot.getValue(Drinker.class);
                        }
                        else {
                            drinker = snapshot.getValue(Merchant.class);
                        }
                        if(drinker != null)
                        {
                            drinker.id = snapshot.getKey();
                            int lastOrder = drinker.orderHistory.size() - 1;
                            for(int i=lastOrder; i>=0; i--)
                            {
                                Order prevOrder = drinker.orderHistory.get(i);
                                addLabel(prevOrder);
                                List<Item> orderItems = prevOrder.items;
                                for(Item item: orderItems)
                                {
                                    addItem(item);
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
    public void addLabel(Order order)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = "-----" + order.getStoreName() + "----- " + order.getTime().toString();
        orderItem = new Button(this);
        orderItem.setText(itemContent);
        orderItem.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(orderItem);
    }
    public void addItem(Item item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name + "             " + item.caffeine + "mg" + "            $" + item.price;
        orderItem = new Button(this);
        orderItem.setText(itemContent);
        orderItem.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(orderItem);
    }
}