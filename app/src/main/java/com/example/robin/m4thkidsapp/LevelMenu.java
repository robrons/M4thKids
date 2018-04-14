package com.example.robin.m4thkidsapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
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
import android.widget.TextView;

import java.util.Random;

public class LevelMenu extends AppCompatActivity {
    Dialog myDialog;
    MediaPlayer mySong;
    boolean music = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        mySong = MediaPlayer.create(LevelMenu.this, R.raw.buddy);
        mySong.isLooping();
        mySong.start();
        setContentView(R.layout.activity_topic_menu);
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

    @Override
    public void onPause() {
        super.onPause();
        mySong.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!music) {
            mySong.start();
        }
    }

    //Pop Up Menu
    public void ShowPopup(View v) {
        TextView txtclose;
        TextView txtmusic;
        myDialog.setContentView(R.layout.custompopup);
        txtclose = myDialog.findViewById(R.id.resume);
        txtmusic = myDialog.findViewById(R.id.music);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        //Music Button
        txtmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music) {
                    myDialog.findViewById(R.id.linearLayout6).setBackgroundColor(Color.parseColor("#85e085"));
                    ((TextView) myDialog.findViewById(R.id.ll6text)).setText("On");
                    mySong.start();
                    music = false;
                } else {
                    myDialog.findViewById(R.id.linearLayout6).setBackgroundColor(Color.parseColor("#ff9999"));
                    ((TextView) myDialog.findViewById(R.id.ll6text)).setText("Off");
                    mySong.pause();
                    music = true;
                }
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}

