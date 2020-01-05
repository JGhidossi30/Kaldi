package com.appfactory.kaldi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Database
{
    private static final Database instance = new Database();
    private List<Drinker> drinkers;
    private List<Merchant> merchants;
    private DatabaseReference database;

    public static Database getInstance() {
        return instance;
    }

    private Database()
    {
        drinkers = new ArrayList<Drinker>();
        database = FirebaseDatabase.getInstance().getReference("users");
        database.child("drinkers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    if (snapshot.exists())
                    {
                        drinkers.add(snapshot.getValue(Drinker.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("onCancelled: ", databaseError.toString());
            }
        });

        merchants = new ArrayList<Merchant>();
        database.child("merchants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    if (snapshot.exists())
                    {
                        merchants.add(snapshot.getValue(Merchant.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("onCancelled: ", databaseError.toString());
            }
        });
    }

    public void addMerchant(Merchant m)
    {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateDatabase()
    {
        if (CurrentUser.getInstance().getNullDrinkerMerchant() == 1)
        {
            Drinker drinker = new Drinker();
            drinker.name = CurrentUser.getInstance().getName();
            drinker.email = CurrentUser.getInstance().getEmail();
            drinker.password = CurrentUser.getInstance().getPassword();
            drinker.dailyCaffeine = CurrentUser.getInstance().getDailyCaffeine();
            drinker.orderHistory = CurrentUser.getInstance().getOrderHistory();
//            drinker.cart = CurrentUser.getInstance().getCart();
            drinker.id = CurrentUser.getInstance().getId();

            database.child("drinkers").child(CurrentUser.getInstance().getId()).setValue(drinker);
        }
        else if (CurrentUser.getInstance().getNullDrinkerMerchant() == 2)
        {
            Merchant merchant = new Merchant();
            merchant.name = CurrentUser.getInstance().getName();
            merchant.email = CurrentUser.getInstance().getEmail();
            merchant.password = CurrentUser.getInstance().getPassword();
            merchant.dailyCaffeine = CurrentUser.getInstance().getDailyCaffeine();
            merchant.orderHistory = CurrentUser.getInstance().getOrderHistory();
//            merchant.cart = CurrentUser.getInstance().getCart();
            merchant.id = CurrentUser.getInstance().getId();
            merchant.stores = CurrentUser.getInstance().getStores();

            database.child("merchants").child(CurrentUser.getInstance().getId()).setValue(merchant);
        }
    }

    public List<Merchant> getMerchants()
    {
        return merchants;
    }

    //    public void storage()
//    {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://your-app-name.com");
//
//        Uri file = Uri.fromFile(new File("data/data/file-path/file-name"));
//        Log.d("file", file.getPath());
//
//
//        StorageReference riversRef = storageRef.child("firebase-storage");
//
//        UploadTask uploadTask = riversRef.putFile(file);
//
//// Register observers to listen for when the download is done or if it fails
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//                Log.d("uploadFail", "" + exception);
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                sendNotification("upload backup", 1);
//
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//                Log.d("downloadUrl", "" + downloadUrl);
//            }
//        });
//    }
}