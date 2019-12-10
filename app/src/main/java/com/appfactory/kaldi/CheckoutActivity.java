package com.appfactory.kaldi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity
{
    private ArrayList<Button> cartItems = new ArrayList<Button>();
    List<Item> shoppingCart;
    private Button cartItem;
    private Button checkout;
    private Item removeItem;
    private Order newOrder;
    String businessTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        businessTitle = getIntent().getStringExtra("businessTitle");
        checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if((shoppingCart != null) && (shoppingCart.size() > 0))
                {
                    updateDrinkerOrderHistory();
                    updateStoreOrderHistory();
//                    emptyCart();
                    int userType = CurrentUser.getInstance().getNullDrinkerMerchant();
                    if (userType == 1)
                    {
                        Intent myIntent = new Intent(CheckoutActivity.this, DrinkerMainActivity.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Intent myIntent = new Intent(CheckoutActivity.this, MerchantMainActivity.class);
                        startActivityForResult(myIntent, 0);
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Cart is empty!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        });
        updateCart();
        displayToaster();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent myIntent = new Intent(CheckoutActivity.this, MenuActivity.class);
        myIntent.putExtra("businessTitle", businessTitle);
        startActivityForResult(myIntent, 0);
    }

    public void updateCart()
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
                            Order cart = drinker.cart.get(businessTitle);
                            if(cart != null)
                            {
                                shoppingCart = cart.items;
                                // Remove any item from the cart and updates db
                                if (removeItem != null) {
                                    updateDateBase(drinker, cart, shoppingCart);
                                }
                                for (Item cartItem : shoppingCart) {
                                    addMenuItem(cartItem);
                                }
                            }
                        }
                    }
                    break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    // Updates the store order history
    public void updateStoreOrderHistory()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Merchant merchant = snapshot.getValue(Merchant.class);
                    if ((merchant != null) && (merchant.stores != null))
                    {
                        for (int i = 0; i < merchant.stores.size(); i++)
                        {
                            Store store = merchant.stores.get(i);
                            if(store.storeName.equals(businessTitle))
                            {
                                merchant.id = snapshot.getKey();
                                Log.d("merch Id", "" + merchant.id);
                                store.orderHistory.add(newOrder);
                                merchant.submitToDatabase();
                                break;
                            }
                        }
                    }
                    break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    // Updates the drinker order history
    public void updateDrinkerOrderHistory()
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
                        if ((drinker != null))
                        {
                            drinker.id = snapshot.getKey();
                            newOrder = drinker.cart.get(businessTitle);
//                            newOrder.setTime(LocalTime.now().toString());
                            drinker.orderHistory.add(newOrder);
                            drinker.submitToDatabase();
                        }
                    }
                    break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    // Removes the cart from the database
    public void emptyCart()
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
                        if ((drinker != null))
                        {
                            drinker.id = snapshot.getKey();
                            drinker.cart.remove(businessTitle);
                            drinker.submitToDatabase();
                        }
                    }
                    break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    public void updateDateBase(Drinker drinker, Order cart, List<Item> cartItems)
    {
        Log.d("before", "" + cartItems.size());
        for(Item item: cartItems)
        {
            if(item.name.equals(removeItem.name))
            {
                cartItems.remove(item);
                break;
            }
        }
        Log.d("after", "" + cartItems.size());
        cart.items = cartItems;
        if(cartItems.size() > 0)
        {
            drinker.cart.put(businessTitle, cart);
        }
        else
        {
            drinker.cart.remove(businessTitle);
        }
        drinker.submitToDatabase();
    }

    public void displayToaster()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Click on the item to remove it!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void removeItems()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        for(Button button: cartItems)
        {
            layout.removeView(button);
        }
    }

    public void addMenuItem(Item item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name + "             " + item.caffeine + "mg" + "            $" + item.price;
        cartItem = new Button(this);
        cartItem.setText(itemContent);
        cartItem.setBackgroundColor(Color.WHITE);
        layout.addView(cartItem);
        cartItems.add(cartItem);
        cartItem.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Please make your selection").setCancelable(true)
                        .setPositiveButton("Remove Item", new DialogInterface.OnClickListener()
                        {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                            {
                                removeItem = item;
                                removeItems();
                                updateCart();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                            {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}