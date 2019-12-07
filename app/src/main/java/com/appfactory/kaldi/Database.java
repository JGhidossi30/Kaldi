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
    private List<Merchant> merchants;
    private DatabaseReference database;


    public static Database getInstance() {
        return instance;
    }

    private Database()
    {
        merchants = new ArrayList<Merchant>();
        database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
        database.addValueEventListener(new ValueEventListener() {
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

    public void updateDatabase()
    {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
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

    public List<Merchant> getMerchants()
    {
        return merchants;
    }
}