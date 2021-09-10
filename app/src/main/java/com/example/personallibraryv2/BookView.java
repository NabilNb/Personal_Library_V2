package com.example.personallibraryv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookView extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference dbReference;
    EditText name;
    EditText author;
    TextView status;
    String intentInfo;
    Button edit;
    DatabaseReference bookInfo;
    String classInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);


        Intent intent=getIntent();
        String key=intent.getStringExtra("key");
        name=(EditText) findViewById(R.id.editTextName);
        author=(EditText) findViewById(R.id.editTextAuthor);
        status=(TextView)findViewById(R.id.statusInfo);
        name.setEnabled(false);
        author.setEnabled(false);
        edit=(Button)findViewById(R.id.buttonEdit);


        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbReference=database.getReference("books");
        bookInfo=dbReference.child(key);

        updateInfo();

        classInfo=intent.getStringExtra("info");


    }

    public void updateInfo() {
        bookInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Books boi=snapshot.getValue(Books.class);
                name.setText(boi.getName());
                author.setText(boi.getAuthor());
                status.setText(boi.getHasRead());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void goBack(View view) {
        if(classInfo.equalsIgnoreCase("collection")){
            Intent changeActivity=new Intent(this,Collection.class);
            startActivity(changeActivity);
        }else{
            Intent changeActivity=new Intent(this,ReadingList.class);
            startActivity(changeActivity);
        }
    }

    public void editInfo(View view) {
        if(edit.getText().toString().equals("Edit")){
            edit.setText("Update");
            name.setEnabled(true);
            author.setEnabled(true);


        }else{
            edit.setText("Edit");
            bookInfo.child("name").setValue(name.getText().toString());
            bookInfo.child("author").setValue(author.getText().toString());
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            name.setEnabled(false);
            author.setEnabled(false);
        }




    }
}