package com.example.lauronmidtermexamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button reset;
    static TextView turn_status;
    static int[] color = new int[]{Color.RED, Color.BLUE};
    static TableRow change;

    static char[][] box = new char[][]{
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
    };

    static Button[][] btns;
    static boolean isPLayer1;
    static int moveCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveCount = 0;
        isPLayer1 = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        change = findViewById(R.id.indicator);
        reset = findViewById(R.id.reset_btn);

        change.setBackgroundColor(color[1]);
        turn_status = findViewById(R.id.turn_info);

        btns = new Button[][]{
                {findViewById(R.id.btn1),findViewById(R.id.btn2),findViewById(R.id.btn3)},
                {findViewById(R.id.btn4),findViewById(R.id.btn5),findViewById(R.id.btn6)},
                {findViewById(R.id.btn7),findViewById(R.id.btn8),findViewById(R.id.btn9)},
        };

        for (int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                int finalX = x;
                int finalY = y;
                btns[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickBox(finalX, finalY);
                    }
                });
            }
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                setAllClickable();
            }
        });

        reset();
        setAllClickable();
    }
    static void clickBox(int x, int y){
        if (moveCount == 9 ){
            if (isWinner()){
                turn_status.setText("Player "+ ((isPLayer1)? "O": "X") + " WINS THE GAME");
                setAllNotClickable();
                return;
            }
            else {
                turn_status.setText("Stalemate");
                setAllNotClickable();
                btns[x][y].setText(isPLayer1? "O" : "X");
                return;
            }
        }
        else{
            btns[x][y].setText(isPLayer1? "O" : "X");
            box[x][y] = isPLayer1?'O':'X';
            btns[x][y].setClickable(false);
            change.setBackgroundColor(color[isPLayer1?0:1]);
            if (isWinner()){
                change.setBackgroundColor(color[isPLayer1?1:0]);
                turn_status.setText("Player "+ ((isPLayer1)? "O": "X") + " WINS THE GAME");
                moveCount = 9;
                setAllNotClickable();
                return;
            }
        }
        moveCount++;

        if (!isWinner() && moveCount >= 9){
            btns[x][y].setText(isPLayer1? "O" : "X");
            turn_status.setText("Stalemate");
            return;
        }

        isPLayer1 = !isPLayer1;
        turn_status.setText("Player "+(isPLayer1?"O":"X")+"'s Turn");
    }

    static void reset(){
        isPLayer1 = true;
        moveCount = 0;
        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){
                box[x][y] = ' ';
            }
        }
        setAllClickable();
        change.setBackgroundColor(Color.BLUE);
        turn_status.setText("O's Turn");
    }
    static void setAllClickable(){
        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){
                btns[x][y].setClickable(true);
                btns[x][y].setText("");
            }
        }
    }
    static void setAllNotClickable(){
        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){
                btns[x][y].setClickable(false);
            }
        }
    }
    static boolean isWinner(){
        char pla = isPLayer1?'O':'X';
        for (int x=0; x<3; x++){
            if(box[x][0] == pla && box[x][1] == pla && box[x][2] == pla ){
                return true;
            }
        }
        for (int x=0; x<3; x++){
            if(box[0][x] == pla && box[1][x] == pla && box[2][x] == pla ){
                return true;
            }
        }
        if(box[0][0] == pla && box[1][1] == pla && box[2][2] == pla ){
            return true;
        }
        if(box[0][2] == pla && box[1][1] == pla && box[2][0] == pla ){
            return true;
        }
        return false;
    }
}