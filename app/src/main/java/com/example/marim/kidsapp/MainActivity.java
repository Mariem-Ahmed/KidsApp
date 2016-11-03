package com.example.marim.kidsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton alpha;
    ImageButton num;
    ImageButton day;
    ImageButton month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alpha = (ImageButton) findViewById(R.id.alphabetBtn);
        num = (ImageButton) findViewById(R.id.numBtn);
        day = (ImageButton) findViewById(R.id.dayBtn);
        month = (ImageButton) findViewById(R.id.monthBtn);

        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlphabetActivity.class);
                startActivity(intent);
            }
        });

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(intent);
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DayActivity.class);
                startActivity(intent);
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MonthActivity.class);
                startActivity(intent);
            }
        });
    }
}
