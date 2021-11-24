package com.example.p1sqliteindianrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListViewOfUsers extends AppCompatActivity {

    DatabaseHealper databaseHealper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHealper =new DatabaseHealper(this);
        setContentView(R.layout.activity_list_view_of_users);
        List<String> data= databaseHealper.getData();
        ListView lv =findViewById(R.id.listView);
        ArrayAdapter<String> arr;
        arr= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, data);
        lv.setAdapter(arr);

    }
}