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

              String [] arrayAnswers2 = new String[] {"Jill”, “Sara”, “Carol”, “Vicky"};
              String [] array2= {"Easy", "comparing", "multiple choice", "Jill has *BLANK* cats. Sara has *BLANK* cats. Carol has *BLANK* cats. Vicky has *BLANK* cats. Who has the *MOST* cats?", null, Arrays.toString(arrayAnswers2), "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);

              String [] arrayAnswers3 = new String[] {"Zebras”, “Elephants", "Lions", "Gorillas"};
              String [] array3= {"Easy", "comparing", "multiple choice", "The zoo has *BLANK* zebras. It also has *BLANK* elephants, *BLANK* lions, and *BLANK* gorillas. Are there *MORE* zebras, elephants, lions, or gorillas at the zoo?", null, Arrays.toString(arrayAnswers3), "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);

              String [] arrayAnswers4 = {"frisbees”, “soccer balls”, “footballs”, “basketballs"};
              String [] array4= {"Easy", "comparing", "multiple choice", "Jeremy has *BLANK* frisbees, *BLANK* soccer balls, *BLANK* footballs, and *BLANK* basketballs in his garage. Which does he have the *LEAST* of?", null, Arrays.toString(arrayAnswers4), "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);


          }

        finish();
    }
}
