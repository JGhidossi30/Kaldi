//package com.appfactory.kaldi;
//
//import android.content.Intent;
//import android.view.Gravity;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class Database
//{
//    private ReentrantLock lock = new ReentrantLock();
//    private List<Merchant> merchants;
//
//    public void updateMerchants()
//    {
//        lock.lock();
//        try
//        {
//            DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
//            database.addValueEventListener(new ValueEventListener()
//            {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                    {
//                        if (snapshot.exists())
//                        {
//                            Merchant merchant = snapshot.getValue(Merchant.class);
//                            drinker.id = snapshot.getKey();
//                            if (!drinker.password.equals(password))
//                            {
////                                                Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
////                                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
////                                                toast.show();
//                            }
//                            else if (drinker.email.equals(email))
//                            {
//                                //Update Page
//                                Intent myIntent;
//                                if (id == 1) {
//                                    myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
//                                    myIntent.putExtra("currentUser", id);
//                                    myIntent.putExtra("isDrinker", true);
//                                }
//                                else {
//                                    myIntent = new Intent(view.getContext(), MerchantMainActivity.class);
//                                    myIntent.putExtra("currentUser", id);
//                                    myIntent.putExtra("isDrinker", false);
//
//                                }
//                                myIntent.putExtra("currentUser", drinker.id);
//                                startActivity(myIntent);
//                                break;
//                            }
//                            else
//                            {System.out.println("----------  Account does not exist!");
////                                                Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
////                                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
////                                                toast.show();
//                            }
//                        }
//                        else
//                        {
//                            Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//                            toast.show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) { }
//            });
//        }
//        finally
//        {
//            lock.unlock();
//        }
//    }
//
//    public void j()
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
//
//    public void updateDatabase()
//    {
//
//    }
//
//    public List<Merchant> getMerchants()
//    {
//        lock.lock();
//        try
//        {
//            DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child("merchants");
//            database.addValueEventListener(new ValueEventListener()
//            {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                    {
//                        if (snapshot.exists())
//                        {
//                            Merchant merchant = snapshot.getValue(Merchant.class);
//                            drinker.id = snapshot.getKey();
//                            if (!drinker.password.equals(password))
//                            {
////                                                Toast toast = Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_LONG);
////                                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
////                                                toast.show();
//                            }
//                            else if (drinker.email.equals(email))
//                            {
//                                //Update Page
//                                Intent myIntent;
//                                if (id == 1) {
//                                    myIntent = new Intent(view.getContext(), DrinkerMainActivity.class);
//                                    myIntent.putExtra("currentUser", id);
//                                    myIntent.putExtra("isDrinker", true);
//                                }
//                                else {
//                                    myIntent = new Intent(view.getContext(), MerchantMainActivity.class);
//                                    myIntent.putExtra("currentUser", id);
//                                    myIntent.putExtra("isDrinker", false);
//
//                                }
//                                myIntent.putExtra("currentUser", drinker.id);
//                                startActivity(myIntent);
//                                break;
//                            }
//                            else
//                            {System.out.println("----------  Account does not exist!");
////                                                Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
////                                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
////                                                toast.show();
//                            }
//                        }
//                        else
//                        {
//                            Toast toast = Toast.makeText(getApplicationContext(), "Account does not exist!", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//                            toast.show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) { }
//            });
//        }
//        finally
//        {
//            lock.unlock();
//        }
//        return this.merchants;
//    }
//}