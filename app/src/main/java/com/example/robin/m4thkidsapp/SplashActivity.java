/*
  Created by robin on 2/11/2018.
 */
package com.example.robin.m4thkidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        if(DbHelper.getsInstance(getApplicationContext()).check_initialSound()) {

            DbHelper.getsInstance(getApplicationContext()).initialSound();

            String [] arrayAnswers1 = new String[] {"Sally", "Bill", "Kate", "Joe"};
            String [] array1= {"Easy", "comparing", "multiple choice", "Sally has *BLANK* apples. Bill has *BLANK* apples. Kate has *BLANK* apples. Joe has *BLANK* apples. Who has the *MOST* apples?", null, Arrays.toString(arrayAnswers1), "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);

            String [] arrayAnswers2 = new String[] {"Jill", "Sara", "Carol", "Vicky"};
            String [] array2= {"Easy", "comparing", "multiple choice", "Jill has *BLANK* cats. Sara has *BLANK* cats. Carol has *BLANK* cats. Vicky has *BLANK* cats. Who has the *MOST* cats?", null, Arrays.toString(arrayAnswers2), "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);

            String [] arrayAnswers3 = new String[] {"Zebras", "Elephants", "Lions", "Gorillas"};
            String [] array3= {"Easy", "comparing", "multiple choice", "The zoo has *BLANK* zebras. It also has *BLANK* elephants, *BLANK* lions, and *BLANK* gorillas. Which kind of animal is there the *MOST* of?", null, Arrays.toString(arrayAnswers3), "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);

            String [] arrayAnswers4 = {"frisbees", "soccer balls", "footballs", "basketballs"};
            String [] array4= {"Easy", "comparing", "multiple choice", "Jeremy has *BLANK* frisbees, *BLANK* soccer balls, *BLANK* footballs, and *BLANK* basketballs in his garage. Which does he have the *LEAST* of?", null, Arrays.toString(arrayAnswers4), "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);

            String [] array5= {"Easy", "counting", "multiple choice", "What number is *AFTER* *BLANK* ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);

            String [] array6= {"Easy", "counting", "multiple choice", "What number is *AFTER* *BLANK* ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);

            String [] array7= {"Easy", "counting", "multiple choice", "What number comes *BEFORE* *BLANK* ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array7);

            String [] array8= {"Easy", "counting", "multiple choice", "What number comes *BEFORE* *BLANK* ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array8);

            String [] array9= {"Easy", "counting", "multiple choice", "What number comes *AFTER* *BLANK* ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array9);

            String [] array10= {"Medium", "addition", "multiple choice", "*BLANK* + *BLANK* = ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array10);

            String [] array11= {"Medium", "adding", "multiple choice", "*BLANK* + *BLANK* + *BLANK* + *BLANK* = ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array11);

            String [] array12= {"Medium", "adding", "multiple choice", "*BLANK* bananas + *BLANK* bananas = ? bananas", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array12);

            String [] array13= {"Medium", "adding", "multiple choice", " It’s your birthday! If you started with *BLANK* video games, got *BLANK* more video games from your friends, *BLANK*  from your parents, how many video games do you have now?* ", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array13);

            String [] array14= {"Medium", "subtracting", "multiple choice", "Chris has *BLANK* apples. He gives *BLANK* to a homeless person, and *BLANK* to a friend. How many does he have left?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array14);

            String [] array15= {"Medium", "subtracting", "multiple choice", "*BLANK* - *BLANK* = ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array15);

            String [] array16= {"Medium", "subtracting", "multiple choice", "*BLANK* - *BLANK* - *BLANK* - *BLANK* = ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array16);

            String [] array17= {"Medium", "subtracting", "multiple choice", "*BLANK* chairs - *BLANK* chairs = ? chairs", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array17);

            String [] array18= {"Hard", "times", "multiple choice", "*BLANK* x *BLANK* is how much?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array18);

            String [] array19= {"Hard", "times", "multiple choice", "*BLANK* groups of *BLANK* is how many total?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array19);

            String [] array20= {"Hard", "divide", "multiple choice", "*BLANK* / *BLANK* = ?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array20);

            String [] array21= {"Hard", "divide", "multiple choice", "*BLANK* pieces of candy are divided into *BLANK* piles, how many pieces are in each pile?", null, null, "green", "no", "Yes", "no"};
            DbHelper.getsInstance(getApplicationContext()).addQuestion(array21);


        }

        finish();
    }
}