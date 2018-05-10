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
        //get "subtable
        if(topic.equals("Review")) {
            String difficultyString = "Easy";
            switch(level) {
                case 0: difficultyString = "Easy";
                    break;
                case 1: difficultyString = "Medium";
                    break;
                case 2: difficultyString = "Hard";
                    break;
            }
            questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withDifficulty(difficultyString);

        }
        else {
            questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withLesson(topic);
        }
        //make list with question Ids
        qID = new ArrayList<>();
        for (int i = 0; i < questionSet.size(); i++) {
            qID.add(i);
        }
        if (questionSet.size() < 5) {
            for(int i = questionSet.size(); i < 5; i++)
            {
                qID.add(i%questionSet.size());
            }

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
            topic = question.get(1);
            //generates the completed question without the *BLANK* (basically it comes up with the numbers
            completeQuestion = generateQuestion(question.get(2), "multiple choice");


            //This takes the random numbers and generates the answer (and potentially possible answers) for the question
            answers = generateAnswers(completeQuestion, "multiple choice", question.get(4));

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
    List generateAnswers(List numbers, String questionType, String answer_names)
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

            if(topic.equals("counting"))
            {
                int direction = 0;
                if(completeQuestion.get(0).toString().contains("*BEFORE*"))
                {
                    completeQuestion.add(0, completeQuestion.get(0).toString().replaceFirst(Pattern.quote("*BEFORE*"), "before"));
                    completeQuestion.remove(1);
                    direction = -1;
                }
                else if(completeQuestion.get(0).toString().contains("*AFTER*"))
                {
                    completeQuestion.add(0, completeQuestion.get(0).toString().replaceFirst(Pattern.quote("*AFTER*"), "after"));
                    completeQuestion.remove(1);
                    direction = 1;
                }
                answer = Integer.parseInt(numbers.get(1).toString()) + direction;
            }

            else if (topic.equals("comparing"))
            {
                int compareGreater = 0;
                answer = 0;
                answer_names.replace("[", "");
                answer_names.replace("]", "");
                answer_names.replace(" ", "");

                System.out.println(answer_names);

                String names[] = answer_names.substring(1, answer_names.length()-1).split(",");
                String str2[] = answer_names.split(",");

                //calculates answer by adding all the numbers that were generated
                if(completeQuestion.get(0).toString().contains("*MOST*"))
                {
                    completeQuestion.add(0, completeQuestion.get(0).toString().replaceFirst(Pattern.quote("*MOST*"), "most"));
                    completeQuestion.remove(1);
                    compareGreater = 1;
                    answer = 1;
                }
                else
                {
                    completeQuestion.add(0, completeQuestion.get(0).toString().replaceFirst(Pattern.quote("*LEAST*"), "least"));
                    completeQuestion.remove(1);
                    compareGreater = 0;
                    answer = 1;
                }
                for(int i = 1; i < numbers.size(); i++) {
                    if (compareGreater == 1)
                    {
                        int one = Integer.parseInt(numbers.get(i).toString());
                        int two = Integer.parseInt(numbers.get(answer+1).toString());
                        if(one > two)
                            answer = i - 1;
                    }
                    else {
                        if (Integer.parseInt(numbers.get(i).toString()) < Integer.parseInt(numbers.get(answer+1).toString()))
                            answer = i - 1;
                    }
                }
                finalAnswer = names[answer];
                //adds answer to the list of possible answers
                List <String> answerString = new ArrayList<>();
                for (int i = 0; i < 4; i ++)
                {
                    answerString.add(names[i]);

                }

                //shuffles the array of possible answers so they will be displayed in a random order
                Collections.shuffle(answerString);

                //adds answer again to the end so the calling function can know what the answer is
                answerString.add(finalAnswer);
                return answerString;


            }
            else if (topic.equals("adding")) {
                //calculates answer by adding all the numbers that were generated
                for (int i = 1; i < numbers.size(); i++) {
                    answer += Integer.parseInt(numbers.get(i).toString());
                }
            }
            else if (topic.equals("subtracting")) {
                answer = Integer.parseInt(numbers.get(1).toString());
                //calculates answer by adding all the numbers that were generated
                for (int i = 2; i < numbers.size(); i++) {
                    answer -= Integer.parseInt(numbers.get(i).toString());
                }
            }
            else if (topic.equals("divide")) {
                answer = Integer.parseInt(numbers.get(1).toString());
                //calculates answer by adding all the numbers that were generated
                for (int i = 2; i < numbers.size(); i++) {
                    answer /= Integer.parseInt(numbers.get(i).toString());
                }
            }
            else if (topic.equals("times")) {
                answer = 1;
                //calculates answer by adding all the numbers that were generated
                for (int i = 1; i < numbers.size(); i++) {
                    answer *= Integer.parseInt(numbers.get(i).toString());
                }
            }
            finalAnswer = Integer.toString(answer);
            //adds answer to the list of possible answers
            answers.add(answer);

            //max bound for possible answer
            max = answer + 10;
            //min bound for possible answer
            min = answer - 10;

            if (min < 0)
                min = min * -1;


            //Loops for number of possible answers it will display (Right now thats 4)
            int count = 0;
            while (count != 3) {

                Random rand = new Random();
                int randNum = rand.nextInt(max + 1 - min) + min;

                if (!answers.contains(randNum)) {
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
        int randNum = 1;
        //makes a list and populates it with that many random numbers for 0-10
        List n = new ArrayList();
        if(topic.equals("divide") || topic.equals("subtracting"))
        {
            Random rand = new Random();
            int rand1 = rand.nextInt(9) + 1;
            n.add(rand1);
            for(int i=0; n.size() < numOfNumbers; i++)
            {

                rand1 = Integer.parseInt(n.get(0).toString());
                int rand2 = rand.nextInt(9)+1;
                n.remove(0);
                n.add(0, rand2);
                if(topic.equals("subtracting"))
                    n.add(0, rand1+rand2);
                else
                    n.add(0, rand1*rand2);


                // n.add(rand.nextInt(10));
            }
        }

        else{

            int count = 0;
            while(count < numOfNumbers)
            {
                Random rand = new Random();
                int randnum = 0;
                if(topic.equals("counting"))
                    randnum = rand.nextInt(49) + 1;
                else
                    randnum = rand.nextInt(10);
                if(topic.equals("comparing"))
                { if(!n.contains(randnum)) {
                    //add a random possible answer to the list
                    n.add(randnum);
                    count = count + 1;
                }

                }
                else {
                    n.add(randnum);
                    count = count + 1;
                }

            }
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
