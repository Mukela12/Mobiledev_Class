package com.example.beanshop;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomePage extends AppCompatActivity {
    Button arabic,burnside,caturra,excelsa,geisha,maracatu;
    Button aboutUsButton;
    Boolean nightMode = false;
    private UiModeManager uiModeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);

        arabic = (Button) findViewById(R.id.arabica);
        burnside = (Button) findViewById(R.id.burnside);
        caturra = (Button) findViewById(R.id.caturra);
        excelsa = (Button) findViewById(R.id.excelsa);
        geisha = (Button) findViewById(R.id.geisha);
        maracatu = (Button) findViewById(R.id.maracatu);


        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Arabic";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
            }
        });



        burnside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Burnside";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
            }
        });


        caturra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Caturra";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
            }
        });



        maracatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Maracatu";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
            }
        });



        excelsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Excelsa";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
            }
        });

        geisha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Buy.class);
                String coffee = "Geisha";
                intent.putExtra("Bean", coffee);
                startActivity(intent);
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
                    selectedFragment = new MainFragment();
                    break;
            }

            switch (item.getItemId()){
                case R.id.account:
                    Intent intent = new Intent(getApplicationContext(), MyAccountPage.class);
                    startActivity(intent);
                    break;
            }

            switch (item.getItemId()){
                case R.id.theme:
                    
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