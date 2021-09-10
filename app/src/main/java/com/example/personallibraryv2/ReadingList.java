package com.example.personallibraryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadingList extends AppCompatActivity {

    ArrayList<String> boi;
    ArrayList<Books> boiObject;
    List<String> keys= new ArrayList<String>();
    ArrayAdapter<String> boiAdapter;
    ListView list;
    FirebaseDatabase database;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        boi=new ArrayList<String>();
        boiObject=new ArrayList<Books>();
        boiAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,boi);

        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbReference=database.getReference("books");
        readBooks();
        //DBHelper dbh=new DBHelper();
        //dbh.readBooks();
        //update(dbh.bookObjecct);

        list=(ListView)findViewById(R.id.listViewRL);
        list.setAdapter(boiAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=keys.get(position);
                Intent infoBook=new Intent(ReadingList.this,BookView.class);
                infoBook.putExtra("key",str);
                infoBook.putExtra("info","ReadingList");
                startActivity(infoBook);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String str=keys.get(position);
                DatabaseReference readingUpdate=dbReference.child(str);
                readingUpdate.child("hasRead").setValue("Read");
                Toast.makeText(ReadingList.this, "Information Removed Successfully", Toast.LENGTH_SHORT).show();
                readBooks();


                return true;
            }
        });



    }



    public void readBooks(){
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boi.clear();
                boiObject.clear();
                keys.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                    Books boo=keyNode.getValue(Books.class);
                    String str=boo.getName();

                    if(boo.getHasRead().equalsIgnoreCase("Ongoing")) {
                        boi.add(str);
                        keys.add(keyNode.getKey());
                        boiObject.add(boo);
                    }
                }
                boiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void homePage(View view) {
        Intent changeHome=new Intent(this,MainActivity.class);
        startActivity(changeHome);
    }

    public void goToAdd(View view) {
    }
}