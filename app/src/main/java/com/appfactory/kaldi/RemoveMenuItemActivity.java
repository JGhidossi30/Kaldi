package com.appfactory.kaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveMenuItemActivity extends AppCompatActivity {

    private Button newStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_menu_item);

        getStoreMenu();
    }

    public void getStoreMenu()
    {
        for (Merchant merchant : Database.getInstance().getMerchants())
        {
            if (CurrentUser.getInstance().getId().equals(merchant.id))
            {
                for (int i = 0; i < merchant.stores.size(); i++)
                {
                    if (merchant.stores.get(i).getStoreName().equals(getIntent().getStringExtra("businessTitle")))
                    {
                        for (int j = 0; j < merchant.stores.get(i).menu.size(); j++) {
                            addMenuItem(merchant.stores.get(i).menu.get(j), i, j);
                        }
                    }
                }
            }
        }
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
//        database.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Merchant merchant = postSnapshot.getValue(Merchant.class);
//                    if ((merchant != null) && CurrentUser.getInstance().getStores().get(i).equals()CurrentUser.getInstance().getId().equals(merchant.id)) {
//                        if (merchant.stores != null) {
//                            for (int i = 0; i < merchant.stores.size(); i++)
//                            {
//                                addMenuItem(merchant.stores.get(i), i);
//                            }
//                        }
//                        else {
//                            Toast toast = Toast.makeText(getApplicationContext(), "Merchant has no stores!", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//                            toast.show();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    public void addMenuItem(Item item, int i, int j)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = item.name;
        newStore = new Button(this);
        newStore.setText(itemContent);
        newStore.setBackgroundColor(Color.WHITE);
        layout.addView(newStore);
        newStore.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
                database.child(CurrentUser.getInstance().getId()).child("stores").child(Integer.toString(i)).child("menu").child(Integer.toString(j)).removeValue();

                Intent myIntent = new Intent(view.getContext(), RemoveMenuItemActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}