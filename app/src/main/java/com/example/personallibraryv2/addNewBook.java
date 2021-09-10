package com.example.personallibraryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class addNewBook extends AppCompatActivity {
    EditText nameField;
    EditText authorField;
    //CheckBox box;
    FirebaseDatabase database;
    DatabaseReference dbReference;
    Spinner spin;
    Spinner spinStatus;
    ArrayList<String> status;
    ArrayList<String> list;
    ArrayAdapter spinAdapter;
    ArrayAdapter spinAdapterList;
    String classInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);
        nameField=(EditText)findViewById(R.id.addEditTextName);
        authorField=(EditText)findViewById(R.id.addEditTextAuthor);
        //box=(CheckBox)findViewById(R.id.addcheckBox);
        database=FirebaseDatabase.getInstance("https://personal-library-491-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbReference=database.getReference("books");

        spin=(Spinner)findViewById(R.id.spinner);

        status=new ArrayList<>();
        status.add("Did not read");
        status.add("Ongoing");
        status.add("Read");
        spinAdapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,status);


        spin.setAdapter(spinAdapter);

        spinStatus=(Spinner)findViewById(R.id.spinner2);
        list=new ArrayList<>();
        list.add("books");
        list.add("wishlist");
        spinAdapterList=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);

        spinStatus.setAdapter(spinAdapterList);
        Intent intent=getIntent();
        classInfo=intent.getStringExtra("class");



    }

    public void goBack(View view) {
        if(classInfo.equalsIgnoreCase("collection")){
            Intent changeActivity=new Intent(this,Collection.class);
            startActivity(changeActivity);
        }else{
            Intent changeActivity=new Intent(this,WishList.class);
            startActivity(changeActivity);
        }



    }

    public void createBook(View view) {
        String ref=spinStatus.getSelectedItem().toString();
        dbReference=database.getReference(ref);



        String name=nameField.getText().toString();
        String author=authorField.getText().toString();
        //String flag=""+ box.isChecked();
        //int idx=spin.getSelectedItemPosition();
        String str=spin.getSelectedItem().toString();

        Books boi=new Books(name,author,str);
        DatabaseReference newBook=dbReference.push();
        newBook.setValue(boi);
        Toast.makeText(this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
        nameField.setText("");
        authorField.setText("");

}
}