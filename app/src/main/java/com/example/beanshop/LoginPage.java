package com.example.beanshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    EditText usernameLogin, passwordLogin;
    Button signupButton1, loginButton;
    DBHelper Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameLogin = (EditText) findViewById(R.id.username);
        passwordLogin = (EditText) findViewById(R.id.password);
        signupButton1 = (Button) findViewById(R.id.signup);
        loginButton = (Button) findViewById(R.id.logins);
        Database = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                if (username.equals("") || password.equals(""))
                    Toast.makeText(LoginPage.this, "Please fill in all details.", Toast.LENGTH_SHORT).show();
                else {
                    Log.d("TAG", "Username: " + username + " Password: " + password);

                    Boolean finduser = Database.passwordValidation(username, password);
                    Log.d("TAG", "Username: " + username + " Password: " + password);

                    Log.d("TAG", "myBoolean: " + finduser);


                    if (finduser == true) {
                        Toast.makeText(LoginPage.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginPage.this, "User not found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpPage.class);
                startActivity(intent);
            }
        });
    }
}
