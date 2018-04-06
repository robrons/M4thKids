package com.example.robin.m4thkidsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions extends AppCompatActivity {

    public static int level;
    public static String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get subtable
        List<List<String>> questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withLesson(topic);

        //make list with question Ids
        List<Integer> qID = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qID.add(i);
        }

        //shuffle list so questions are in a different order each times
        Collections.shuffle(qID);

        for(int i = 0; i < 5; i++) {

            List<String> question = questionSet.get(qID.get(i));

            //Check Question Type
            if(question.get(0) == "multiple choice");
            {

                setContentView(R.layout.activity_questions);
                RadioButton Rbutton = (RadioButton)findViewById(R.id.answer_a);
                Rbutton.setText(R.string.counting);

            }
          /*  if(question.get(0) == "graphic");
            {
                setContentView(R.layout.activity_questions);
            }
            if(question.get(0) == "fill in the blank num");
            {
                setContentView(R.layout.activity_questions);
            }
            if(question.get(0) == "fill in the blank word");
            {
                setContentView(R.layout.activity_questions);
            }
*/
        }


        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }*/

    }

}
