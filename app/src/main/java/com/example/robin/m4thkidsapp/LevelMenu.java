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

public class LevelMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
    }

    public void buttonOnClickEasy(View v) {
        topic_menu t = new topic_menu();
        t.level = 0;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);


    }
    public void buttonOnClickMedium(View v) {
        topic_menu t = new topic_menu();
        t.level = 1;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);

    }
    public void buttonOnClickHard(View v) {
        topic_menu t = new topic_menu();
        t.level = 2;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);

    }

}

