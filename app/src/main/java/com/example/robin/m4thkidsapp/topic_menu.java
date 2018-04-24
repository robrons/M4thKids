package com.example.robin.m4thkidsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class topic_menu extends AppCompatActivity {
    public static int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getApplicationContext().deleteDatabase("math4kids.db");
        /*for(int i = 0; i < 10; i ++)
        {
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
        }*/

        setContentView(R.layout.activity_topic_menu);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        if (level == 0) {
            button.setText(R.string.comparing);
            button2.setText(R.string.counting);
        }
        if (level == 1) {
            button.setText(R.string.adding);
            button2.setText(R.string.subing);
        }
        if (level == 2) {
            button.setText(R.string.times);
            button2.setText(R.string.divide);
        }

    }

    // On Instantiation

//comment
    public void buttonOnClick(View v) {
        Questions.topic = ((Button) v).getText().toString();
        Questions.level = level;
        if(Questions.topic.equals("Review") || Questions.topic.equals("Logic"))
        {
            Questions.topic += level;
        }
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }
}