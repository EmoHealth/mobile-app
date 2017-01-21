package com.example.marx.emohealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
    }
}
