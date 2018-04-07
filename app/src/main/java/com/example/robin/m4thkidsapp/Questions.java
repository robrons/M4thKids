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

        //get "subtable"
        List<List<String>> questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withLesson(topic);

        //make list with question Ids
        List<Integer> qID = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qID.add(i);
        }

        //shuffle list so questions are in a different order each times
        Collections.shuffle(qID);

        //for loop to loop through the number of questions in a lesson (its 5 questions right now)
        for(int i = 0; i < 5; i++) {

            //pulls the random question from the subtable
            List<String> question = questionSet.get(qID.get(i));

            //Check Question Type
            if(question.get(0).equals("multiple choice"))
            {
                //generates the completed question without the *BLANK* (basically it comes up with the numbers
                List completeQuestion = generateQuestion(question.get(1), "multiple choice");

                //This takes the random numbers and generates the answer (and potentially possible answers) for the question
                List answers = generateAnswers(completeQuestion, "multiple choice");

                //This acutally makes the display page
                setContentView(R.layout.activity_questions);

                //gets the first radio button and sents it to the value of the answer
                RadioButton Rbutton = (RadioButton)findViewById(R.id.answer_a);
                Rbutton.setText(answers.get(0).toString());

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

    //this funcation takes in a list that has the complete question as the first element and then
    //the number that were generated to fill the blanks in with. It only cares about those numbers
    //no the question so it needs to start at 1 when getting info from numbers list
    //it takes in the question type so it knows if it needs to make possible answers (for multiple
    //choice) or if the answer needs the be a word like "ten" or a number like "10"
    List generateAnswers(List numbers, String questionType)
    {
        //this will the list it returns
        List returnList = new ArrayList();

        //checks question type
        if(questionType.equals("Multiple Choice"))
        {
         //checks topic so that it knows which operation it needs to preform to find the answer
         if (topic.equals("adding"))
         {
             //filler code
             returnList.add("5");
         }
        }
        return returnList;
    }

    //fills the "*BLANK*"s in the question in with numbers
    List generateQuestion(String question, String questionType)
        {
            //finds how many *BLANK*s so it knows how many numbers to generate
            int numOfNumbers = countOccurences(question, "*BLANK*");

            //makes a list and populates it with that many random numbers for 0-10
            List n = new ArrayList();
            for(int i=0; i < numOfNumbers; i++)
            {
                Random rand = new Random();
                n.add(rand.nextInt(10));
            }

            //newQuestion will be the qustion with the *BLANk*s filled in with the rand generated
            //numers
            String newQuestion = question;

            //replaces *BLANK* with the rand numbers
            for(int i =0; i < numOfNumbers; i++) {
                newQuestion = newQuestion.replaceFirst(Pattern.quote("*BLANK*"), n.get(i).toString());
            }

            //Makes the list it will return. The first element in the list will be the completed
            //question, then each of the following elements will be one of the numbers it replaced
            // *BLANK* with.
            List returnList = new ArrayList();

            //First element is the completed question
            returnList.add(newQuestion);

            //The following elements are the rand numbers
            for(int i = 0; i < numOfNumbers; i++)
            {
                returnList.add(n.get(i));
            }

            //returns that list
            return returnList;
        }

    //copied from https://www.geeksforgeeks.org/count-occurrences-of-a-word-in-string/
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
