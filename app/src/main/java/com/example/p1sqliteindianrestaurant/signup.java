package com.example.p1sqliteindianrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {


    DatabaseHealper databaseHealper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup= findViewById(R.id.button2);
        databaseHealper =new DatabaseHealper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= getStrnig(R.id.sigupEd1);
                String num =getStrnig(R.id.sigupEd2);
                String pass =getStrnig(R.id.sigupEd3);
                String email= getStrnig(R.id.sigupEd4);
                boolean flag=true;
                if(!FunctionCollection.checkNumber(num))
                {
                    flag=false;
                    Toast.makeText(signup.this, "** Please Enter \n a valid number", Toast.LENGTH_SHORT).show();
                }
                if(!FunctionCollection.isValid(email))
                {
                    flag=false;
                    Toast.makeText(signup.this, "** Please Enter \n a Email Address", Toast.LENGTH_SHORT).show();
                }
                if(flag)
                {
                    boolean insertData=  databaseHealper.addData(name,num,pass,email);
                    //Toast.makeText(signup.this, name+" "+num+" "+pass+"  "+email, Toast.LENGTH_SHORT).show();
                    if(insertData)
                    {
                        Toast.makeText(signup.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(myIntent);
                    }
                    else
                    {
                        Toast.makeText(signup.this, "User already exists in records ", Toast.LENGTH_SHORT).show();

                        setText(R.id.sigupEd1);
                        setText(R.id.sigupEd2);
                        setText(R.id.sigupEd3);
                        setText(R.id.sigupEd4);
                    }
                }
            }
        });
    }
    void setText(int id)
    {
        EditText ed= findViewById(id);
        ed.setText("");
    }
    String getStrnig(int id)
    {
        EditText ed= findViewById(id);
        return ed.getText().toString();
    }
}