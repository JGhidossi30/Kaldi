package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class MenuActivity extends AppCompatActivity
{
    private Button newItem;
    private Button checkout;
    private ArrayList <Item> myCart = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String businessTitle = getIntent().getStringExtra("businessTitle");
        getMenu(businessTitle);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent myIntent = new Intent(MenuActivity.this, DrinkerMainActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void getMenu(String businessTitle)
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Merchant merchant = postSnapshot.getValue(Merchant.class);
                    if ((merchant != null) && (merchant.stores != null))
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
                                    checkout.setVisibility(View.INVISIBLE);
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
        checkout = (Button) findViewById(R.id.cart);
        checkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
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
                                if (userType == 1) {
                                    drinker = snapshot.getValue(Drinker.class);
                                } else {
                                    drinker = snapshot.getValue(Merchant.class);
                                }
                                if (drinker != null)
                                {
                                    drinker.id = snapshot.getKey();
                                    Order cartItems = drinker.cart.get(businessTitle);
                                    // Adds new items to the cart if the user selected anything
                                    if(myCart.size() > 0)
                                    {
                                        // Creates a new cart
                                        if (cartItems == null) {
                                            cartItems = new Order();
                                            cartItems.items = myCart;
                                            cartItems.storeName = businessTitle;
                                            drinker.cart.put(businessTitle, cartItems);
                                        }
                                        // Adds items to old cart
                                        else {
                                            cartItems.items.addAll(myCart);
                                            drinker.cart.put(businessTitle, cartItems);
                                        }
                                        Toast toast = Toast.makeText(getApplicationContext(), "Added items to cart!", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    }
                                    // Determines whether the cart has any items
                                    if(cartItems == null)
                                    {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Cart is empty!", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    }
                                    else
                                    {
                                        drinker.submitToDatabase();
                                        Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class);
                                        myIntent.putExtra("businessTitle", businessTitle);
                                        startActivityForResult(myIntent, 0);
                                    }
                                }
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });
            }
        });
    }
    public void addMenuItem(Item item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name + "             " + item.caffeine + "mg" + "            $" + item.price;
        newItem = new Button(this);
        newItem.setText(itemContent);
        newItem.setBackgroundColor(Color.WHITE);
        layout.addView(newItem);
        newItem.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                myCart.add(item);
            }
        });
    }
}