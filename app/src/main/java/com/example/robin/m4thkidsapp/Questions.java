package com.example.robin.m4thkidsapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Questions extends AppCompatActivity {

    public static String topic;
    public static int level;
    private String finalAnswer;
    private List answers;
    private List completeQuestion;
    private ConstraintLayout mainview;
    Dialog myDialog;
    List<List<String>> questionSet = null;
    List<Integer> qID;
    int questionsCompleted = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        myDialog = new Dialog(this);
        mainview = (ConstraintLayout) this.findViewById(R.id.questions);
       //get "subtable"
       questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withLesson(topic);

       //make list with question Ids
       qID = new ArrayList<>();
       for (int i = 0; i < 5; i++) {
           qID.add(i);
       }

       //shuffle list so questions are in a different order each times
       Collections.shuffle(qID);
       createQuestionInfo(questionSet.get(qID.get(0)));
   }

   public void goThroughQuestions(View v)
   {
       //for loop to loop through the number of questions in a lesson (its 5 questions right now)
       if(questionsCompleted < 5) {

           //pulls the random question from the subtable
           List<String> question = questionSet.get(qID.get(questionsCompleted));
           createQuestionInfo(question);
       }
       else
           setContentView(R.layout.end_lesson);
   }

   void displayMulitpleChoice()
   {

       //This acutally makes the display page
       setContentView(mainview);


       //This Displays the question
       TextView QuestionBox = (TextView) findViewById(R.id.QuestionBox);
       QuestionBox.setText(completeQuestion.get(0).toString());

       //gets the first radio button and sents it to the value of the answer
       List<RadioButton> RadioButtonList= new ArrayList<RadioButton>();
       RadioButtonList.add((RadioButton)findViewById(R.id.answer_a));
       RadioButtonList.add((RadioButton)findViewById(R.id.answer_b));
       RadioButtonList.add((RadioButton)findViewById(R.id.answer_c));
       RadioButtonList.add((RadioButton)findViewById(R.id.answer_d));

       for(int j = 0; j < RadioButtonList.size(); j++)
       {
           RadioButtonList.get(j).setChecked(false);
           RadioButtonList.get(j).setText(answers.get(j).toString());

       }

   }

  void createQuestionInfo(List<String> question)
   {
            //Check Question Type
            if(question.get(0).equals("multiple choice"))
            {
                //generates the completed question without the *BLANK* (basically it comes up with the numbers
                completeQuestion = generateQuestion(question.get(1), "multiple choice");

                //This takes the random numbers and generates the answer (and potentially possible answers) for the question
                answers = generateAnswers(completeQuestion, "multiple choice");
                displayMulitpleChoice();


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

    //this function takes in a list that has the complete question as the first element and then
    //the number that were generated to fill the blanks in with. It only cares about those numbers
    //no the question so it needs to start at 1 when getting info from numbers list
    //it takes in the question type so it knows if it needs to make possible answers (for multiple
    //choice) or if the answer needs the be a word like "ten" or a number like "10"
    List generateAnswers(List numbers, String questionType)
    {
        //this will the list it returns
        List<Integer> answers = new ArrayList();
        int answer = 0;
        int min = 0;
        int max = 0;
        //checks question type
        if(questionType.equals("multiple choice"))
        {
         //checks topic so that it knows which operation it needs to preform to find the answer
         if (topic.equals("adding"))
         {
             //calculates answer by adding all the numbers that were generated
             for(int i = 1; i < numbers.size(); i++)
             {
                 answer += Integer.parseInt(numbers.get(i).toString());
             }
             finalAnswer = Integer.toString(answer);
             //adds answer to the list of possible answers
             answers.add(answer);

             //max bound for possible answer
              max = answer + 10;
             //min bound for possible answer
              min = answer - 10;

             if (min < 0)
                 min = 0;


             //Loops for number of possible answers it will display (Right now thats 4)

         }
            if (topic.equals("times"))
            {
                answer = 1;
                //calculates answer by adding all the numbers that were generated
                for(int i = 1; i < numbers.size(); i++)
                {
                    answer *= Integer.parseInt(numbers.get(i).toString());
                }
                finalAnswer = Integer.toString(answer);
                //adds answer to the list of possible answers
                answers.add(answer);

                //max bound for possible answer
                 max = answer + 10;
                //min bound for possible answer
                 min = answer - 10;

                if (min < 0)
                    min = 0;
            }



             //Loops for number of possible answers it will display (Right now thats 4)
             int count = 0;
             while(count != 3)
             {

                 Random rand = new Random();
                 int randNum = rand.nextInt(max + 1 - min) + min;

                 if(!answers.contains(randNum)) {
                     //add a random possible answer to the list
                     answers.add(randNum);
                     count = count + 1;
                 }


             }

             //shuffles the array of possible answers so they will be displayed in a random order
            Collections.shuffle(answers);

             //adds answer again to the end so the calling function can know what the answer is
             answers.add(answer);

        }
        return answers;
    }

    //fills the "*BLANK*"s in the question in with numbers
    List generateQuestion(String question, String questionType)
        {
            //finds how many *BLANK*s so it knows how many numbers to generate
            int numOfNumbers = countOccurrences(question, "*BLANK*");

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
    static int countOccurrences(String str, String word)
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

    public void gradeOnClick(View view) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.RGroup);
        // Is the button now checked?
        int selectedRadioButtonID = rg.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
        String selectedAnswer = selectedRadioButton.getText().toString();
        grade(selectedAnswer, view);


    }

    void grade(String choice, View v)
    {
        if(choice.equals(finalAnswer)) {
            questionsCompleted = questionsCompleted + 1;
            setContentView(R.layout.correct_answer);
        }
        else
            ShowWrongAnswerPopup(v);
    }

    public void endOfLesson (View v)
    {
        topic_menu t = new topic_menu();
        topic_menu.level = level;
        Intent intent = new Intent(this, topic_menu.class);
        startActivity(intent);
    }


    //Pop Up Menu
    public void ShowWrongAnswerPopup(View v) {
        TextView txtclose;
        TextView txtmusic;
        myDialog.setContentView(R.layout.wrong_answer_popup);
        txtclose = myDialog.findViewById(R.id.tryagain);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
