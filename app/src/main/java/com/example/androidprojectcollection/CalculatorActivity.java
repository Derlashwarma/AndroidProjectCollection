package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {
    static MyCalculator calculator;
    TextView equation;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        equation = findViewById(R.id.equation);
        answer = findViewById(R.id.result);

        equation.setText("0");
        answer.setText("0");

        calculator = new MyCalculator(equation,answer);
    }
    public void addToDisplay(View v){
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        if(buttonText.equalsIgnoreCase("delete")){
            calculator.remove();
            return;
        }
        if(buttonText.equals(".")){
            calculator.appendDot();
            return;
        }
        calculator.append(buttonText);
    }
}