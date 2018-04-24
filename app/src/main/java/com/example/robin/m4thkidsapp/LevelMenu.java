package com.example.robin.m4thkidsapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

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
        if(getIntent().getBooleanExtra("musicOn",true)){
            mySong.start();
            music = false;
        }
        else {
            music = true;
        }

        setContentView(R.layout.activity_topic_menu);
        setContentView(R.layout.activity_level_menu);
    }

    public void buttonOnClickEasy(View v) {
        topic_menu.level = 0;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);


    }

    public void buttonOnClickMedium(View v) {
        topic_menu t = new topic_menu();
        topic_menu.level = 1;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);

    }

    public void buttonOnClickHard(View v) {
        topic_menu t = new topic_menu();
        topic_menu.level = 2;
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
        TextView deleteDB =  myDialog.findViewById((R.id.deletedb));
        deleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDatabase(v);
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

    public void deleteDatabase(View v){
        DbHelper.getsInstance(getApplicationContext()).death(this);
        Context context = getApplicationContext();
        CharSequence text = "Database Deleted";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

