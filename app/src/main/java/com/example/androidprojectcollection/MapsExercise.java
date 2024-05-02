package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MapsExercise extends AppCompatActivity {
    ConstraintLayout main_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exercise);

        main_container = findViewById(R.id.main_background);

        findViewById(R.id.location1_btn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.589238784731858, 124.3055640455393"));
            main_container.setBackgroundResource(R.drawable.location1background);
            startActivity(intent);
        });

        findViewById(R.id.location2_btn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:17.57288030243463, 120.38915560879663"));
            main_container.setBackgroundResource(R.drawable.location2background);
            startActivity(intent);
        });

        findViewById(R.id.location3_btn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-33.7990848772431, 19.316060103786633"));
            main_container.setBackgroundResource(R.drawable.location3background);
            startActivity(intent);
        });

        findViewById(R.id.location4_btn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-13.163021188751289, -72.54530501897774"));
            main_container.setBackgroundResource(R.drawable.location4background);
            startActivity(intent);
        });

        findViewById(R.id.location5_btn).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:59.99092690477368, 29.77711055841815"));
            main_container.setBackgroundResource(R.drawable.location5background);
            startActivity(intent);
        });

    }
}