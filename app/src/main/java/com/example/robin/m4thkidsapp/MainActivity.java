package com.example.robin.m4thkidsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClickTrue(View v){
        Intent intent = new Intent(this, LevelMenu.class);
        intent.putExtra("musicOn",true);
        startActivity(intent);
    }

    public void buttonOnClickFalse(View v){
        Intent intent = new Intent(this, LevelMenu.class);
        intent.putExtra("musicOn",false);
        startActivity(intent);
    }
}