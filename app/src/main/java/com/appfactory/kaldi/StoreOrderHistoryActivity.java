package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StoreOrderHistoryActivity extends AppCompatActivity {

    private Button orderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_history);
        displayHistory();
    }

    public void displayHistory()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        String userName = CurrentUser.getInstance().getId();
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
                            for (int k = 0; k < merchant.stores.size(); k++)
                            {
                                if (merchant.stores.get(k).getStoreName().equals(getIntent().getStringExtra("storeName"))) {
                                    int lastOrder = merchant.stores.get(k).orderHistory.size() - 1;
                                    for (int i = lastOrder; i >= 0; i--) {
                                        Order prevOrder = merchant.stores.get(i).orderHistory.get(i);
                                        addLabel(prevOrder);
                                        List<Item> orderItems = prevOrder.items;
                                        for (Item item : orderItems) {
                                            addItem(item);
                                        }
                                    }
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
        String itemContent = "-----" + order.getStoreName() + "----- " + order.getTime();
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