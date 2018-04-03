package com.example.robin.m4thkidsapp;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class topic_menu extends AppCompatActivity {
    public static int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_menu);
        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        if(level == 0) {
            button.setText(R.string.comparing);
            button2.setText(R.string.counting);
        }
        if(level == 1) {
            button.setText(R.string.adding);
            button2.setText(R.string.subing);
        }
        if(level == 2) {
            button.setText(R.string.times);
            button2.setText(R.string.divide);
        }

    }
//comment
    public void buttonOnClick(View v) {
        Button button1 = (Button) v;
        ((Button) v).setText("This will take you to a game");
    }
}