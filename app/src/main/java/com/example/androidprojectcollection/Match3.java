package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Match3 extends AppCompatActivity {
    private static ImageView[][] buttons;
    LinearLayout player1_contaier;
    LinearLayout player2_contaier;
    TextView p1Score;
    private static int[][] values = new int[5][5];
    private static int[] colors = new int[]{R.drawable.red_button,R.drawable.green_button,R.drawable.blue_button,R.drawable.yellow_button};
    Button reset;

    private static int previousX = -1;
    private static int previousY = -1;
    private static boolean isPLayer1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);
        player1_contaier = findViewById(R.id.player1Container);
        p1Score = findViewById(R.id.p1_score);

        buttons = new ImageView[][]{
                {findViewById(R.id.btn1),findViewById(R.id.btn2),findViewById(R.id.btn3),findViewById(R.id.btn4),findViewById(R.id.btn5)},
                {findViewById(R.id.btn6),findViewById(R.id.btn7),findViewById(R.id.btn8),findViewById(R.id.btn9),findViewById(R.id.btn10)},
                {findViewById(R.id.btn11),findViewById(R.id.btn12),findViewById(R.id.btn13),findViewById(R.id.btn14),findViewById(R.id.btn15)},
                {findViewById(R.id.btn16),findViewById(R.id.btn17),findViewById(R.id.btn18),findViewById(R.id.btn19),findViewById(R.id.btn20)},
                {findViewById(R.id.btn21),findViewById(R.id.btn22),findViewById(R.id.btn23),findViewById(R.id.btn24),findViewById(R.id.btn25)},
        };
        reset = findViewById(R.id.reset_btn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPLayer1 = true;
                generateRandomValues();
                colorBoxes();
                player1_contaier.setBackgroundColor(Color.parseColor("#fffee3"));
                p1Score.setText("0");
                checkColumn();
                checkRow();
            }
        });

        for (int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                int finalX = x;
                int finalY = y;
                buttons[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(previousX < 0 && previousY < 0){
                            previousX = finalX;
                            previousY = finalY;
                            setAllNotClickable();
                            setAdjacentClickable(finalX,finalY);
                        }
                        else{
                            swap(previousX,finalX,previousY,finalY);
                            previousX = -1;
                            previousY = -1;
                            setAllClickable();
                            while(checkRow());
                            while(checkColumn());
                        }
                    }
                });
            }
        }
        generateRandomValues();
        colorBoxes();
        while(checkRow());
        while(checkColumn());
        setAllClickable();
    }
    private boolean checkColumn(){
        for (int x=0; x<5; x++){
            for (int y=1; y<4; y++){
                if((values[x][y] == values[x][y+1]) && (values[x][y] == values[x][y-1])){
                    randomizeValues(x,x,x,y,y+1,y-1);
                    addScore();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRow(){
        boolean found = false;
        for (int y=0; y<5; y++){
            for (int x=1; x<4; x++){
                if((values[x][y] == values[x+1][y]) && (values[x][y] == values[x-1][y])){
                    randomizeValues(x,x+1,x-1,y,y,y);
                    addScore();
                    return true;
                }
            }
        }
        return false;
    }
    private void addScore(){
            int player1Score = Integer.parseInt(p1Score.getText().toString());
            p1Score.setText(String.format("%d",++player1Score));
    }
    private void randomizeValues(int x1,int x2,int x3, int y1, int y2, int y3){
        Random r = new Random();
        values[x1][y1] = r.nextInt(4);
        values[x2][y2] = r.nextInt(4);
        values[x3][y3] = r.nextInt(4);
        colorBoxes();
    }

    private void swap(int x1, int x2, int y1, int y2){
        int temp = values[x1][y1];
        values[x1][y1] = values[x2][y2];
        values[x2][y2] = temp;
    }

    private void generateRandomValues(){
        Random r = new Random();
        for(int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                int random = r.nextInt(4);
                values[x][y] = random;
            }
        }
    }
    private void colorBoxes(){
        for(int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                int val = values[x][y];
                buttons[x][y].setBackgroundResource(colors[val]);
            }
        }
    }
    private void setAllNotClickable(){
        for(int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                buttons[x][y].setClickable(false);
                //buttons[x][y].setBackgroundResource(R.drawable.blue_button);
            }
        }
    }
    private void setAllClickable(){
        for(int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                buttons[x][y].setClickable(true);
                int val = values[x][y];
                buttons[x][y].setBackgroundResource(colors[val]);
            }
        }
    }
    private void setAdjacentClickable(int x, int y){
        try{
            buttons[x+1][y].setClickable(true);
           // buttons[x+1][y].setBackgroundResource(R.drawable.green_button);
        }
        catch (Exception e){}
        try{
            buttons[x-1][y].setClickable(true);
           // buttons[x-1][y].setBackgroundResource(R.drawable.green_button);
        }
        catch (Exception e){}
        try{
            buttons[x][y+1].setClickable(true);
            //buttons[x][y+1].setBackgroundResource(R.drawable.green_button);
        }
        catch (Exception e){}
        try{
            buttons[x][y-1].setClickable(true);
           // buttons[x][y-1].setBackgroundResource(R.drawable.green_button);
        }
        catch (Exception e){}
    }
}