package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button layoutbtn;
    Button btnActivity;
    Button calculatorBtn;
    Button match3Btn;
    Button passing_intents_btn;
    Button midterm_btn;

    Button menus_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutbtn = (Button)findViewById(R.id.layout_activity_btn);
        layoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LayoutExcercise.class);
                startActivity(intent);
            }
        });
        btnActivity = (Button)findViewById(R.id.btn_activity);
        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ButtonActivity.class);
                startActivity(intent);
            }
        });

        calculatorBtn = (Button) findViewById(R.id.calculator_activity);
        calculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        midterm_btn = (Button) findViewById(R.id.midterm_btn);
        midterm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicTacToe.class);
                startActivity(intent);
            }
        });

        match3Btn = findViewById(R.id.match3_btn);
        match3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Match3.class));
            }
        });

        passing_intents_btn = findViewById(R.id.passing_intents_btn);
        passing_intents_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PassingIntentsExercise.class));
            }
        });

        menus_btn = findViewById(R.id.menus_btn);
        menus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuExercise.class));
            }
        });
    }
}