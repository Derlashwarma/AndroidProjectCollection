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

        String currentText = equation.getText().toString();

        String newText;
        if(isOperand(buttonText)){
            newText = currentText.substring(0, currentText.length() - 1) + buttonText.charAt(0);
        }
        else{
            newText = currentText + buttonText;
        }

        equation.setText(newText);
    }

    private boolean isOperand(String operand){
        switch(operand){
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }
}