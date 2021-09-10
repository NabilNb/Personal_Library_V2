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

public class LendInfoView extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dbReference;
    EditText name;
    EditText author;
    EditText lendersName;
    EditText lendersContact;
    EditText lendersEmail;
    String intentInfo;
    Button edit;
    DatabaseReference bookInfo;
    String classInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_info_view);

        Intent intent=getIntent();
        String key=intent.getStringExtra("key");
        name=(EditText) findViewById(R.id.editTextLendBookName);
        author=(EditText) findViewById(R.id.editTextLendAuthorName);
        lendersName=(EditText) findViewById(R.id.editTextLenderName);
        lendersContact=(EditText) findViewById(R.id.editTextLenderContact);
        lendersEmail=(EditText) findViewById(R.id.editTextLenderEmail);



        name.setEnabled(false);
        author.setEnabled(false);
        lendersName.setEnabled(false);
        lendersContact.setEnabled(false);
        lendersEmail.setEnabled(false);
        edit=(Button)findViewById(R.id.buttonLendInfoEdit);


        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbReference=database.getReference("lend");
        bookInfo=dbReference.child(key);

        updateInfo();





    }


    public void updateInfo() {
        bookInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookLendInfo boi=snapshot.getValue(bookLendInfo.class);
                name.setText(boi.getBookName());
                author.setText(boi.getBookAuthor());
                lendersName.setText(boi.getlName());
                lendersContact.setText(boi.getlNumber());
                lendersEmail.setText(boi.getlMail());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goBack(View view) {
        Intent intent=new Intent(this,LendingInfo.class);
        startActivity(intent);
    }

    public void editInfo(View view) {
        if(edit.getText().toString().equals("Edit")){
            edit.setText("Update");
            name.setEnabled(true);
            author.setEnabled(true);
            lendersName.setEnabled(true);
            lendersContact.setEnabled(true);
            lendersEmail.setEnabled(true);

        }else{
            edit.setText("Edit");
            bookInfo.child("BookName").setValue(name.getText().toString());
            bookInfo.child("BookAuthor").setValue(author.getText().toString());
            bookInfo.child("lName").setValue(lendersName.getText().toString());
            bookInfo.child("lNumber").setValue(lendersContact.getText().toString());
            bookInfo.child("lMail").setValue(lendersEmail.getText().toString());

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            name.setEnabled(false);
            author.setEnabled(false);
            lendersName.setEnabled(false);
            lendersContact.setEnabled(false);
            lendersEmail.setEnabled(false);
        }

    }
}