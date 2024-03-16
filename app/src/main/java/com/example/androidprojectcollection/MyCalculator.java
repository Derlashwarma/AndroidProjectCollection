package com.example.androidprojectcollection;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Stack;

public class MyCalculator {
    private StringBuilder operation;
    private StringBuilder answer;

    TextView equation;
    TextView result;
    private boolean canDecimal;
    private static char lastInput;

    public MyCalculator(TextView equation, TextView result) {
        this.result = result;
        this.equation = equation;

        operation = new StringBuilder();
        answer = new StringBuilder();

        operation.append("0");

        canDecimal = true;

        lastInput = ' ';
    }
    public void remove(){
        if (operation.length() == 1){
            operation.setCharAt(0,'0');
            return;
        }
        if(operation.charAt(operation.length()-1) == '.'){
            canDecimal = true;
        }
        operation.deleteCharAt(operation.length()-1);
        equation.setText(operation.toString());
    }
    public void append(String elem){
        if (elem.equals("=")){
            if(operation.length() < 1){
                return;
            }
            try{
                evaluate();
                remove();
                result.setText(answer.toString());
            }
            catch (Exception e){}
            return;
        }
        if(isOperator(elem.charAt(0))){
            canDecimal = true;
        }
        if(isOperator(elem.charAt(0)) && isOperator(last())){
            remove();
        }
        lastInput = elem.charAt(0);
        operation.append(elem);
        equation.setText(operation.toString());
    }
    public void appendDot(){
        if(last() == '.' ){
            remove();
        }
        else if(canDecimal){
            append(".");
            canDecimal = false;
        }
    }
    public String getAnswer() {
        return answer.toString();
    }
    private void evaluate() throws Exception{
        Stack<String> toOperate = new Stack<>();
        Stack<String> operators = new Stack<>();
        char lastOperator = ' ';
        if(isOperator(operation.charAt(operation.length()-1))){
            lastOperator = operation.charAt(operation.length()-1);
            operation.substring(0,operation.length()-1);
        }

        StringBuilder string = new StringBuilder();
        for(int i=0; i<operation.length(); i++){
            char c = operation.charAt(i);
            if(isOperator(c)){
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
                answer = new StringBuilder("Cannot divide by zero");
            }
        }

        Double ans = Double.valueOf(toOperate.pop());
        answer = new StringBuilder(String.format("%.2f",ans));
        operation.append(lastOperator);
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

    private char last(){
        return operation.charAt(operation.length()-1);
    }
}
