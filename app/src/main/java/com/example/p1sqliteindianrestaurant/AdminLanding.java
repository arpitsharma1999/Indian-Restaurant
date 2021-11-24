package com.example.p1sqliteindianrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AdminLanding extends AppCompatActivity {
    DatabaseHealper databaseHealper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);
        Button b1=findViewById(R.id.adminb1);
        databaseHealper =new DatabaseHealper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), ListViewOfUsers.class);
                startActivity(myIntent);
            }
        });
        Button b3=findViewById(R.id.adminb);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed= findViewById(R.id.admine2);
                String name = ed.getText().toString();
                ed=findViewById(R.id.admine);
                String num=ed.getText().toString();
                boolean flag= databaseHealper.isNumExists(num);
                boolean isAmin= databaseHealper.isAdmin(num);
                if(!flag)
                {
                    Toast.makeText(AdminLanding.this, "No record with this number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(isAmin)
                    {
                        Toast.makeText(AdminLanding.this, "Can't change Admin", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        databaseHealper.updateName(num,name);
                        Toast.makeText(AdminLanding.this, "Name changed successfully!!:):)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button b2=findViewById(R.id.adminb2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed = findViewById(R.id.admine1);
                final String num= ed.getText().toString();

                boolean isExist = databaseHealper.isNumExists(num);
                if(!isExist)
                {
                    Toast.makeText(AdminLanding.this, "No record with this number!!", Toast.LENGTH_SHORT).show();
                }
                else if(num.length()>0)
                {
                    boolean deleted =   FunctionCollection.checkNumber(num) && databaseHealper.deleteRow(num);
                    //Toast.makeText(AdminLanding.this, "num", Toast.LENGTH_SHORT).show();
                    if(deleted)
                    {
                        Toast.makeText(AdminLanding.this, "Deleted successfully!! :):)", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AdminLanding.this, "Can not delete Admin's Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}