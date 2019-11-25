package com.appfactory.kaldi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

public class AddMenuItemActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);
//
//        TextView businessNameInput = (TextView) findViewById(R.id.businessName);
//        TextView initialItemInput = (TextView) findViewById(R.id.initialItem);
//        TextView caffeineInput = (TextView) findViewById(R.id.caffeine);
//
//        String businessName = businessNameInput.getText().toString();
//        String initialItem = initialItemInput.getText().toString();
//        String caffeine = caffeineInput.getText().toString();
//        Button addMenuItem =  (Button) findViewById(R.id.addMenuItem);
//        addMenuItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
//                Query search;
////                if (getIntent().getBooleanExtra("isDrinker", true))
////                    search = database.child("drinkers").orderByKey();
////                else
//                    search = database.child("merchants").orderByKey();
//                search.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            if (snapshot.getKey().equals(getIntent().getStringExtra("currentUser"))) {
//                                Merchant merchant;
////                                if (getIntent().getBooleanExtra("isDrinker", true))
////                                    drinker = snapshot.getValue(Drinker.class);
////                                else
//                                    merchant = snapshot.getValue(Merchant.class);
//                                merchant.id = snapshot.getKey();
//                                for (int i = 0; i < merchant.stores.size(); i++)
//                                {
//                                    if (merchant.stores.get(i).storeName.equals(businessName))
//                                    {
//                                        merchant.stores.get(i).menu.addItem(new Item(initialItem, Integer.parseInt(caffeine)));
//                                    }
//                                }
//                                merchant.submitToDatabase();
//
//                                //Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class); Might want to bring this page back
//
////                                if (getIntent().getBooleanExtra("isDrinker", true))
////                                    myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
////                                else
//                                Intent myIntent = myIntent = new Intent(getApplicationContext(), MerchantMainActivity.class);
//                                String currentUser = getIntent().getStringExtra("currentUser");
//                                boolean isDrinker = getIntent().getBooleanExtra("isDrinker", true);
//                                myIntent.putExtra("currentUser", currentUser);
//                                myIntent.putExtra("isDrinker", isDrinker);
//                                startActivityForResult(myIntent, 0);
//
//                                Toast toast = Toast.makeText(getApplicationContext(), "New Business Added!", Toast.LENGTH_LONG);
//                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//                                toast.show();
//                                break;
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
    }

//    /**
//     *
//     */
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//    }
}
