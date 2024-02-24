package com.example.beanshop;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    EditText usernameSignup, passwordSignup, passwordSignup2;
    Button signupButton, backtoLogin,view;
    DBHelper Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        usernameSignup = (EditText) findViewById(R.id.username2);
        passwordSignup = (EditText) findViewById(R.id.password);
        passwordSignup2 = (EditText) findViewById(R.id.password2);
        signupButton = (Button) findViewById(R.id.sign);
        backtoLogin = (Button) findViewById(R.id.backtologin);


        Database = new DBHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String username = usernameSignup.getText().toString();
                String password = passwordSignup.getText().toString();
                String password2 = passwordSignup2.getText().toString();

                if(username.equals("")||password.equals("")||password2.equals(""))
                    Toast.makeText(SignUpPage.this, "Please fill in all details.", Toast.LENGTH_SHORT).show();
                else
                {
                    if(password.equals(password2))
                    {
                        Boolean finduser = Database.usernameValidation(username);

                        Log.d("TAG", "myBooleansign: " + finduser);

                        if (finduser == false) {
                            Boolean insert = Database.insertData(username, password);
                            Log.d("TAG", "inserrt: " + insert);

                            if (insert == true) {
                                Toast.makeText(SignUpPage.this, "Sign-up successful! Welcome.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpPage.this, "Oops! Something went wrong.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpPage.this, "User already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SignUpPage.this, "Please input password again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        backtoLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });
    }
}