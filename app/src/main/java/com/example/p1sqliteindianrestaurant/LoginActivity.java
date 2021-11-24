package com.example.p1sqliteindianrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHealper databaseHealper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button bEnter= findViewById(R.id.loginb1);
        Button newUser= findViewById(R.id.button);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),signup.class);
                startActivity(myIntent);
            }
        });
        databaseHealper =new DatabaseHealper(this);
        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ednum= findViewById(R.id.editText2);
                final String num=ednum.getText().toString();
                ednum=findViewById(R.id.editText);
                final String pass=ednum.getText().toString();
                boolean flage=true;
                if(!FunctionCollection.checkNumber(num))
                {
                    flage =false;

                    Toast.makeText(LoginActivity.this, "Invalid Number!!!", Toast.LENGTH_SHORT).show();

                }
                if(flage) {
                    boolean isNumberExist = databaseHealper.isNumExists(num);
                    if (!isNumberExist) {
                        Toast.makeText(LoginActivity.this, "You are not registered", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        if(!databaseHealper.isAValidPassWithNum(num,pass))
                        {
                            Toast.makeText(LoginActivity.this, "Password does't match with records", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            boolean admin=databaseHealper.isAdmin(num);
                            Intent myIntent;
                            if(admin)
                                myIntent = new Intent(getApplicationContext(),AdminLanding.class);
                            else
                                myIntent=new Intent(getApplicationContext(),userLanding.class);
                            startActivity(myIntent);
                        }
                    }
                }

            }
        });
    }

}