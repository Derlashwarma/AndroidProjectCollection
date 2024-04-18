package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {
    Button close_btn;
    Button toast_btn;
    Button change_bg_btn;
    Button change_btn_bg;
    Button disappear_btn;
    String[] colors = {"red","blue","yellow","green"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        close_btn = (Button)findViewById(R.id.close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this, CloseThis.class);
                startActivity(intent);
            }
        });

        toast_btn = (Button)findViewById(R.id.toast_btn);
        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                String text = "This is a string message";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        change_bg_btn = (Button)findViewById(R.id.change_bg_btn);
        change_bg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int)(Math.random()*(4-0)+0);
                LinearLayout li = (LinearLayout)findViewById(R.id.background);
                li.setBackgroundColor(Color.parseColor(colors[index]));
            }
        });

        change_btn_bg = (Button)findViewById(R.id.change_btn_bg);
        change_btn_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int)(Math.random()*(4-0)+0);
                change_btn_bg.setBackgroundColor(Color.parseColor(colors[index]));
            }
        });

        disappear_btn = (Button)findViewById(R.id.disappear_btn);
        disappear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disappear_btn.setVisibility(View.GONE);
            }
        });

    }
}