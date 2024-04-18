package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {


    TextView[] user_inputs;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);
        TextView fname = findViewById(R.id.fname);
        TextView lname = findViewById(R.id.lname);
        TextView g = findViewById(R.id.g);
        TextView bd = findViewById(R.id.birth_date);
        TextView bp = findViewById(R.id.birth_place);
        TextView mother = findViewById(R.id.m);
        TextView father = findViewById(R.id.f);
        TextView sibling = findViewById(R.id.s);
        TextView phone = findViewById(R.id.p);
        TextView email = findViewById(R.id.e);
        TextView address = findViewById(R.id.a);
//        user_inputs = new EditText[]{
//                findViewById(R.id.fname),findViewById(R.id.lname),findViewById(R.id.g),findViewById(R.id.birth_date),findViewById(R.id.birth_place),
//                findViewById(R.id.m)
//                ,findViewById(R.id.f)
//                ,findViewById(R.id.s),findViewById(R.id.p),findViewById(R.id.e)
//        };

        Intent intent = getIntent();
        String fn = intent.getStringExtra("fn_key");
        fname.setText(fn);

        String ln = intent.getStringExtra("ln_key");
        lname.setText(ln);

        String ge = intent.getStringExtra("gender_key");
        g.setText(ge);

        String birthd = intent.getStringExtra("bd_key");
        bd.setText(birthd);

        String birthp = intent.getStringExtra("bp_key");
        bp.setText(birthp);

        String mo = intent.getStringExtra("m_key");
        mother.setText(mo);

        String fo = intent.getStringExtra("f_key");
        father.setText(fo);

        String so = intent.getStringExtra("s_key");
        sibling.setText(so);

        String po = intent.getStringExtra("n_key");
        phone.setText(po);

        String eo = intent.getStringExtra("e_key");
        email.setText(eo);

        String ao = intent.getStringExtra("a_key");
        address.setText(ao);


        exit = findViewById(R.id.return_intent);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}