package com.example.beanshop;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MyAccountPage extends AppCompatActivity {
    EditText newname, newpassword, oldname, oldpassword;
    Button saveButton, signoutButton, closeappButton;
    DBHelper Database;
    Boolean nightMode = false;
    private UiModeManager uiModeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);

        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);

        newname = (EditText) findViewById(R.id.changeUsername);
        newpassword = (EditText) findViewById(R.id.changePassword);
        oldname = (EditText) findViewById(R.id.oldUsername);
        oldpassword = (EditText) findViewById(R.id.oldPassword);
        saveButton = (Button) findViewById(R.id.savechangesButton);
        signoutButton = (Button) findViewById(R.id.signoutButton);
        closeappButton = (Button) findViewById(R.id.closeappButton);
        Database = new DBHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String newUsername = newname.getText().toString();
                String newPassword = newpassword.getText().toString();
                String oldUsername = oldname.getText().toString();
                String oldPassword = oldpassword.getText().toString();

                if(newUsername.equals("")||newPassword.equals("")||oldUsername.equals("")||oldPassword.equals(""))
                    Toast.makeText(MyAccountPage.this, "Please fill in all details.", Toast.LENGTH_SHORT).show();
                else
                {
                    Boolean updateData = Database.editData(newUsername, newPassword, oldUsername, oldPassword);
                    if(updateData==true)
                    {
                        Toast.makeText(MyAccountPage.this, "Update successful!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MyAccountPage.this, "Update failed. Check inputs.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });

        closeappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomnav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;


            switch (item.getItemId()){
                case R.id.home_page:
                    //selectedFragment = new ButtomNavigation();
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                    break;
            }

            switch (item.getItemId()){
                case R.id.account:
                    //selectedFragment = new ButtomNavigation();
                    Intent intent = new Intent(getApplicationContext(), MyAccountPage.class);
                    startActivity(intent);
                    break;
            }

            switch (item.getItemId()){
                case R.id.theme:
                    //selectedFragment = new ButtomNavigation();
                    if(!nightMode)
                    {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                        nightMode = true;
                    }
                    else if (nightMode)
                    {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                        nightMode = false;
                    }
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragcontainer,selectedFragment).commit();
            return false;
        }
    };
}