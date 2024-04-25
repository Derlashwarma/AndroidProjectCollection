package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MenuExercise extends AppCompatActivity {
    private Button btn_toChange;
    private static int default_height;
    private static int default_width;
    private static boolean disco;
    static float orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btn_toChange = findViewById(R.id.btn_to_change);
        default_height = btn_toChange.getLayoutParams().height;
        default_width = btn_toChange.getLayoutParams().width;

        btn_toChange.setBackgroundTintList(getColorStateList(R.color.teal_700));
        orientation = btn_toChange.getRotationX();

        disco = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ViewGroup.LayoutParams params = btn_toChange.getLayoutParams();

        if(item.getItemId() == R.id.mItemChange) {
            Toast.makeText(this, "Edit Object Item is Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.change_color) {
            btn_toChange.setBackgroundTintList(getColorStateList(R.color.black));
            Toast.makeText(this, "Changing Object Color", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.make_taller) {
            int random = new Random().nextInt(500);
            params.height = btn_toChange.getLayoutParams().height + random;
            btn_toChange.setLayoutParams(params);
            Toast.makeText(this, "Changing Height", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.make_wider) {
            int random = new Random().nextInt(500);
            params.width = btn_toChange.getLayoutParams().width + random;
            btn_toChange.setLayoutParams(params);
            Toast.makeText(this, "Changing Width", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.make_transparent) {
            btn_toChange.setVisibility(View.GONE);
            Toast.makeText(this, "Making Transparent", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.change_orientation) {
            btn_toChange.setRotationX(orientation + 50);
            Toast.makeText(this, "Changing Orientation", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.mItemReset) {
            btn_toChange.setBackgroundColor(Color.GRAY);
            params.height = default_height;
            params.width = default_width;
            disco = false;
            btn_toChange.setLayoutParams(params);
            btn_toChange.setVisibility(View.VISIBLE);
            btn_toChange.setRotationX(orientation);
            btn_toChange.setBackgroundTintList(getColorStateList(R.color.teal_700));
            Toast.makeText(this, "Reset Object Item is Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.mItemExit) {
            finish();
        }
        return true;
    }
}