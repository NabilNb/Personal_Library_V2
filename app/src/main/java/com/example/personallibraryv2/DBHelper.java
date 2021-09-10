package com.example.personallibraryv2;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    FirebaseDatabase fdb;
    DatabaseReference dbr;
    List<Books> bookObjecct=new ArrayList<Books>();
    List<String> keys= new ArrayList<String>();;

    public DBHelper() {
        fdb=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbr=fdb.getReference("books");
        Log.d("TAG", "DBHelper: Called");

    }
    public void readBooks(){
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookObjecct.clear();
                keys.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Books boo=keyNode.getValue(Books.class);
                    bookObjecct.add(boo);


                }

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
