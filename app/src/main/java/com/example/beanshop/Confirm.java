package com.example.beanshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirm extends AppCompatActivity {
    Button button2;
    DBHelper Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpage);

        Database = new DBHelper(this);

        button2 = (Button) findViewById(R.id.back_to_home_button);

        Intent intent = getIntent();
        String value = intent.getStringExtra("Bean");

        Boolean insert = Database.insertcoffeeData(value);

        TextView textView = findViewById(R.id.thank_you_text);
        textView.setText("Thank you for buying " + value);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });

    }

}
