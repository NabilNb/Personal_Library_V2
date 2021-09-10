package com.example.personallibraryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCollection(View view) {
        Intent changeIntent= new Intent(this, Collection.class);
        startActivity(changeIntent);
    }

    public void goToReadingList(View view) {
        Intent changeActivity=new Intent(this,ReadingList.class);
        startActivity(changeActivity);
    }


    public void goToWishlist(View view) {
        Intent changeActivity=new Intent(this,WishList.class);
        startActivity(changeActivity);
    }

    public void goToLendInfo(View view) {
        Intent changeActivity=new Intent(this,LendingInfo.class);
        startActivity(changeActivity);
    }

    public void goToStats(View view) {
        Intent changeActivity=new Intent(this,Statistics.class);
        startActivity(changeActivity);
    }
}