package com.appfactory.kaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String businessTitle = getIntent().getStringExtra("businessTitle");
        System.out.println("------------------title " + businessTitle);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Merchant merchant = postSnapshot.getValue(Merchant.class);
                    for (int i = 0; i < merchant.stores.size(); i++) {
                        if (merchant.stores.get(i).storeName.equals(businessTitle)) {
                            List<Item> menu = merchant.stores.get(i).menu.menu;
                            for (Item item : menu) {
                                System.out.println(item.name + " " + item.caffeine);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayList<String> bag = new ArrayList<String>();

        Button icedCoffee = (Button) findViewById(R.id.iced_coffee);
        icedCoffee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bag.add("Iced Coffee");
            }

        });
        Button americano = (Button) findViewById(R.id.americano);
        americano.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bag.add("Americano");
            }

        });
        Button chaiTea = (Button) findViewById(R.id.chai_tea);
        chaiTea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bag.add("Chai Tea Latte");
            }

        });
        Button greenTea = (Button) findViewById(R.id.green_tea);
        greenTea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bag.add("Green Tea");
            }

        });
        Button blackTea = (Button) findViewById(R.id.black_tea);
        blackTea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bag.add("Black Tea");
            }
        });
        Button checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class);
                myIntent.putExtra("BAG", bag);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}