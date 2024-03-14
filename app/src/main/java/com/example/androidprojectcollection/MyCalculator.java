package com.example.androidprojectcollection;

import android.view.View;
import android.widget.TextView;

import java.util.Stack;

public class MyCalculator {
    private String operation;
    private String answer;
    public MyCalculator() {
        this.operation = "";
        this.answer = "";
    }
    public void remove(View v){
        int size = operation.length();
        try{
            operation = operation.substring(0,size-1);
        }
        catch (Exception e){}
        answer = operation;
    }
    public String getAnswer() {
        System.out.println(answer);
        return answer;
    }
    public void addText(String operation){
        this.operation = operation;
    }
    protected void evaluate() throws Exception{
        Stack<String> toOperate = new Stack<>();
        Stack<String> operators = new Stack<>();
        char lastOperator = ' ';
        if(isOperator(operation.charAt(operation.length()-1))){
            lastOperator = operation.charAt(operation.length()-1);
            this.operation = operation.substring(0,operation.length()-1);
        }

        StringBuilder string = new StringBuilder();
        for(int i=0; i<operation.length(); i++){
            char c = operation.charAt(i);
            if(isOperator(operation.charAt(i))){
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
                answer = "Cannot divide by zero";
            }
        }

        Double ans = new Double(toOperate.pop());
        answer = String.format("%.2f",ans);
        operation += Character.toString(lastOperator);
        System.out.println(answer);
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
    private int precedence(String op) {
        switch(op){
            case "*":
                return 4;
            case "/":
                return 3;
            case "+":
                return 2;
            case "-":
                return 1;

        }
        return 0;
    }
}
