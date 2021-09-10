package com.example.personallibraryv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {
    ArrayAdapter<String> boiAdapter;
    ListView list;
    FirebaseDatabase database;
    DatabaseReference dbBoi;
    DatabaseReference dbWish;
    DatabaseReference dbLend;

    ArrayList<Books> boi=new ArrayList<Books>();
    ArrayList<Books> boiWish=new ArrayList<Books>();

    ArrayList<bookLendInfo> lendObject= new ArrayList<bookLendInfo>();

    ArrayList<String> totalBoi=new ArrayList<>();
    ArrayList<String> totalReading=new ArrayList<>();
    ArrayList<String> totalWish=new ArrayList<>();
    ArrayList<String> totalLend=new ArrayList<>();

    int collection;
    int readingList;
    int wishlist;
    int lent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);



        //boiAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,boi);


        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbBoi=database.getReference("books");
        dbWish=database.getReference("wishlist");
        dbLend=database.getReference("lend");
        readBooks();


    }



    public void readBooks(){
        dbBoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                    Books boo=keyNode.getValue(Books.class);
                    String str=boo.getName();
                    boi.add(boo);
                    totalBoi.add(str);

                    if(boo.getHasRead().equalsIgnoreCase("Ongoing")) {
                        totalReading.add(str);
                    }
                }

             }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbWish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    Books boo=keyNode.getValue(Books.class);
                    boiWish.add(boo);
                    String str=boo.getName();
                    totalWish.add(str);
 }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbLend.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyNode : snapshot.getChildren()){

                    bookLendInfo bookLend=keyNode.getValue(bookLendInfo.class);
                    String str=bookLend.getBookName();
                    lendObject.add(bookLend);
                    totalLend.add(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


    public void homepage(View view) {
        Intent changeHome=new Intent(this,MainActivity.class);
        startActivity(changeHome);
    }

    public void refresh(View view) {

        TextView cView=(TextView)findViewById(R.id.textViewTotalCollection);
        cView.setText(totalBoi.size()+"");

        TextView rView=(TextView)findViewById(R.id.textViewrReadingList);
        rView.setText(totalReading.size()+"");

        TextView wView=(TextView)findViewById(R.id.textViewWishList);
        wView.setText(totalWish.size()+"");

        TextView lView=(TextView)findViewById(R.id.textViewLendInfo);
        lView.setText(totalLend.size()+"");




    }
}