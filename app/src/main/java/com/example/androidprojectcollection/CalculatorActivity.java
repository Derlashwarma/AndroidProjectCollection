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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculator = new MyCalculator();
    }
    public void addToDisplay(View v){
        TextView equation = findViewById(R.id.equation);
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        String currentText = equation.getText().toString();
        EditText result = findViewById(R.id.result);

        String newText;
        if(buttonText.equalsIgnoreCase("delete")){
                calculator.remove(v);
                equation.setText(calculator.getAnswer());
                return;
        }
        if(buttonText.equals("=")){
            calculator.addText(currentText);
            try{
                calculator.evaluate();
            }
            catch(Exception e){
                result.setText("ERROR");
            }
            String answer = calculator.getAnswer();
            Double ans = new Double(0);
            try{
                ans = new Double(answer);
            }
            catch (NumberFormatException e){}
            catch (Exception e){}
            result.setText(String.format("%.2f",ans));
            equation.setText(String.format("%.2f",ans));
            return;
        }

        if(isOperand(buttonText)!=0 && isOperand(Character.toString(currentText.charAt(currentText.length()-1)))!=0){
            newText = currentText.substring(0, currentText.length()-1) + buttonText.charAt(0);
        }
        if(buttonText.equals(".")){
            if (!Character.toString(currentText.charAt(currentText.length() - 1)).equals(".") ||
                    isOperand(Character.toString(currentText.charAt(currentText.length() - 1))) >= 0) {
                newText = currentText + buttonText;
            } else {
                return;
            }
        }
        else{
            newText = currentText + buttonText;
        }
        equation.setText(newText);
    }
    private int isOperand(String operand){
        switch(operand){
            case "+":
                return 2;
            case "-":
                return 1;
            case "*":
                return 4;
            case "/":
                return 3;
        }
        return 0;
    }
}