package com.example.p1sqliteindianrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b= findViewById(R.id.mainB1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(myIntent);
            }
        });
    }
}