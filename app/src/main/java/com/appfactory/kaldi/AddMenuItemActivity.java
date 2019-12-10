package com.appfactory.kaldi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        TextView itemInput = (TextView) findViewById(R.id.itemName);
        TextView caffeineInput = (TextView) findViewById(R.id.caffeine);
        TextView priceInput = (TextView) findViewById(R.id.price);
        Button addMenuItem =  (Button) findViewById(R.id.addButton);
        addMenuItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String businessName = getIntent().getStringExtra("storeName");
                String userName = CurrentUser.getInstance().getId();
                Log.d("user", "" + userName);
                String itemName = itemInput.getText().toString();
                int caffeine = Integer.parseInt(caffeineInput.getText().toString());
                double price = Double.parseDouble(priceInput.getText().toString());
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
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
                                if (merchant != null)
                                {
                                    for (int i = 0; i < merchant.stores.size(); i++)
                                    {
                                        if (merchant.stores.get(i).storeName.equals(businessName))
                                        {
                                            merchant.id = snapshot.getKey();
                                            merchant.stores.get(i).menu.add(new Item(itemName, caffeine, price));
                                            merchant.submitToDatabase();
                                        }
                                        break;
                                    }
                                    Intent myIntent = myIntent = new Intent(getApplicationContext(), StoreProfileActivity.class);
                                    startActivityForResult(myIntent, 0);
                                    Toast toast = Toast.makeText(getApplicationContext(), "New item Added!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                    break;
                                }
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
