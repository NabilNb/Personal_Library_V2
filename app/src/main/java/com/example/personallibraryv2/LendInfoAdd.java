package com.example.personallibraryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LendInfoAdd extends AppCompatActivity {
    EditText bookNameField;
    EditText authorField;
    EditText lName;
    EditText lNumber;
    EditText lMail;

    //CheckBox box;
    FirebaseDatabase database;
    DatabaseReference dbReference;
    ArrayList<String> status;
    ArrayList<String> list;
    String classInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_info_add);
        bookNameField=(EditText)findViewById(R.id.lendEditTextName);
        authorField=(EditText)findViewById(R.id.lendEditTextAuthor);
        lName=(EditText)findViewById(R.id.lendEditTextLName);
        lNumber=(EditText)findViewById(R.id.lendTextPhone);
        lMail=(EditText)findViewById(R.id.lendTextTextEmailaddress);



        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbReference=database.getReference("lend");


    }

    public void createInfo(View view) {
        String bookName=bookNameField.getText().toString();
        String authorName=authorField.getText().toString();
        String lendName=lName.getText().toString();
        String lendNum=lNumber.getText().toString();
        String mail=lMail.getText().toString();

        bookLendInfo newInfo=new bookLendInfo(bookName,authorName,lendName,lendNum,mail);

        DatabaseReference newLendInfo=dbReference.push();
        newLendInfo.setValue(newInfo);
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();

        bookNameField.setText("");
        authorField.setText("");
        lName.setText("");
        lNumber.setText("");
        lMail.setText("");



    }

    public void goBack(View view) {
        Intent changeHome=new Intent(this,LendingInfo.class);
        startActivity(changeHome);
    }
}