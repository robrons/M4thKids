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
import java.util.Random;
import java.util.regex.Pattern;

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

            if(question.get(0).equals("multiple choice"))
            {
                List completeQuestion = generateQuestion(question.get(1), "multiple choice");
                List answers = generateQuestion(completeQuestion, "multiple choice");
                setContentView(R.layout.activity_questions);
                RadioButton Rbutton = (RadioButton)findViewById(R.id.answer_a);
                Rbutton.setText(R.string.counting);

            }
          if(question.get(0).equals("graphic"))
            {
                setContentView(R.layout.activity_questions);
            }
             if(question.get(0) == "fill in the blank num")
            {
                setContentView(R.layout.activity_questions);
            }
            if(question.get(0) == "fill in the blank word")
            {
                setContentView(R.layout.activity_questions);
            }

        }
    }

    List generateQuestion(List numbers, String questionType)
    {
        List returnList = new ArrayList();
        if(questionType.equals("Multiple Choice"))
        {
         if (topic.equals("adding"))
         {
             returnList.add("5");
         }
        }
        return returnList;
    }


    List generateQuestion(String question, String questionType)
        {
            int numOfNumbers = countOccurences(question, "*BLANK*");
            List n = new ArrayList();
            for(int i=0; i < numOfNumbers; i++)
            {
                Random rand = new Random();
                n.add(rand.nextInt(10));
            }
            String newQuestion = new String();
            for(int i =0; i < numOfNumbers; i++) {
                newQuestion = question.replaceFirst(Pattern.quote("*BLANK*"), n.get(i).toString());
            }
            List returnList = new ArrayList();
            returnList.add(newQuestion);
            for(int i = 0; i < numOfNumbers; i++)
            {
                returnList.add(n.get(i));
            }
            return returnList;
        }

        //used from https://www.geeksforgeeks.org/count-occurrences-of-a-word-in-string/
    static int countOccurences(String str, String word)
    {
        // split the string by spaces in a
        String a[] = str.split(" ");

        // search for pattern in a
        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            // if match found increase count
            if (word.equals(a[i]))
                count++;
        }

        return count;
    }

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }*/



}
