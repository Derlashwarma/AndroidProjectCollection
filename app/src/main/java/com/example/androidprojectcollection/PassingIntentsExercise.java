package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PassingIntentsExercise extends AppCompatActivity {

    static RadioGroup genders;
    static EditText[] user_inputs;
    Button clear;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);
        user_inputs = new EditText[]{
            findViewById(R.id.firstname),findViewById(R.id.lastname),findViewById(R.id.birthdate),findViewById(R.id.birthplace),findViewById(R.id.mother)
                ,findViewById(R.id.father),findViewById(R.id.sibling),findViewById(R.id.number),findViewById(R.id.email),findViewById(R.id.address)
        };
        genders = findViewById(R.id.gender_group);

        clear = findViewById(R.id.clear_btn);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText input: user_inputs){
                    input.setText("");
                    genders.clearCheck();
                }
            }
        });

        submit = findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = user_inputs[0].getText().toString();
                String lname = user_inputs[1].getText().toString();
                String birthdate = user_inputs[2].getText().toString();
                String birthplace = user_inputs[3].getText().toString();
                String mother = user_inputs[4].getText().toString();
                String father = user_inputs[5].getText().toString();
                String sibling = user_inputs[6].getText().toString();
                String number = user_inputs[7].getText().toString();
                String email = user_inputs[8].getText().toString();
                String address = user_inputs[9].getText().toString();
                String gender = "";
                RadioButton male = findViewById(R.id.male);
                RadioButton female = findViewById(R.id.female);
                RadioButton other = findViewById(R.id.other);
                if(male.isChecked()){
                    gender = "Male";
                }
                else if(female.isChecked()){
                    gender = "Female";
                }
                else if(other.isChecked()){
                    gender = "Other";
                }

                Intent intent = new Intent( PassingIntentsExercise.this, PassingIntentsExercise2.class);
                intent.putExtra("fn_key",fname);
                intent.putExtra("ln_key",lname);
                intent.putExtra("gender_key",gender);
                intent.putExtra("bd_key",birthdate);
                intent.putExtra("bp_key",birthplace);
                intent.putExtra("m_key",mother);
                intent.putExtra("f_key",father);
                intent.putExtra("s_key",sibling);
                intent.putExtra("n_key",number);
                intent.putExtra("e_key",email);
                intent.putExtra("a_key",address);

                startActivity(intent);
                clea();
            }
        });
    }
    private static void clea(){
        for (EditText input: user_inputs){
            input.setText("");
            genders.clearCheck();
        }
    }
}