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

import java.util.List;

public class RemoveBusinessActivity extends AppCompatActivity
{
    private Button newStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_business);
        getStoreMenu();
    }

    public void getStoreMenu()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Merchant merchant = postSnapshot.getValue(Merchant.class);
                    if ((merchant != null) && CurrentUser.getInstance().getId().equals(merchant.id)) {
                        if (merchant.stores != null) {
                            for (int i = 0; i < merchant.stores.size(); i++)
                            {
                                addMenuItem(merchant.stores.get(i), i);
                            }
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Merchant has no stores!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addMenuItem(Store store, int i)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootLayout);
        String itemContent = store.getStoreName();
        newStore = new Button(this);
        newStore.setText(itemContent);
        newStore.setBackgroundColor(Color.WHITE);
        layout.addView(newStore);
        newStore.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
                database.child(CurrentUser.getInstance().getId()).child("stores").child(Integer.toString(i)).removeValue();

                Intent myIntent = new Intent(view.getContext(), RemoveBusinessActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
