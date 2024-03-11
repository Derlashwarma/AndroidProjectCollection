package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

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
        EditText result = findViewById(R.id.result);

        String newText;
        if(buttonText.equals("=")){
            result.setText(evaluate(currentText));
            return;
        }

        if(isOperand(buttonText)!=0 && isOperand(Character.toString(currentText.charAt(currentText.length()-1)))!=0){
            newText = currentText.substring(0, currentText.length()-1) + buttonText.charAt(0);
        } else if (buttonText.equals(".") && (Character.toString(currentText.charAt(currentText.length()-1)).equals(".")
                || isOperand(Character.toString(currentText.charAt(currentText.length()-1))) >= 0)) {
            return;
        } else{
            newText = currentText + buttonText;
        }
        equation.setText(newText);
    }

    private String evaluate(String expression){
        Stack<String> toOperate = new Stack<>();
        Stack<String> operators = new Stack<>();

        StringBuilder string = new StringBuilder();
        for(int i=0; i<expression.length(); i++){
            char c = expression.charAt(i);
            if(isOperator(expression.charAt(i))){
                toOperate.push(string.toString());
                string = new StringBuilder();

                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(Character.toString(c))) {
                    String operand2 = toOperate.pop();
                    String operand1 = toOperate.pop();
                    String operator = operators.pop();
                    toOperate.push(applyOperator(operand1, operand2, operator));
                }
                operators.push(Character.toString(c));
            } else {
                string.append(Character.toString(c));
            }
        }
        toOperate.push(string.toString());

        while (!operators.isEmpty()) {
            String operand2 = toOperate.pop();
            String operand1 = toOperate.pop();
            String operator = operators.pop();
            try{
                toOperate.push(applyOperator(operand1, operand2, operator));
            }catch(ArithmeticException e){
                return "Cannot divide by zero";
            }
        }
        return toOperate.pop();
    }

    private boolean isOperator(char c) {
        switch (c){
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }


    private String applyOperator(String operand1, String operand2, String operator) throws ArithmeticException{
        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);

        switch (operator) {
            case "+":
                return Double.toString(num1 + num2);
            case "-":
                return Double.toString(num1 - num2);
            case "*":
                return Double.toString(num1 * num2);
            case "/":
                if (num2 != 0) {
                    return Double.toString(num1 / num2);
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
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
    private int precedence(String op) {
        if (op == "*" || op == "/") {
            return 2;
        } else if (op == "+" || op == "-") {
            return 1;
        }
        return 0;
    }
}