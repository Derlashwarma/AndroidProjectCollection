package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void addToDisplay(View v){
        TextView equation = findViewById(R.id.equation);
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        // Assuming the button text is the number you want to add
        String currentText = equation.getText().toString();
        String newText = currentText + buttonText;

        equation.setText(newText);
    }
}