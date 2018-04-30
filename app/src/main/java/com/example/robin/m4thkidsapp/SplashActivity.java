/*
  Created by robin on 2/11/2018.
 */
package com.example.robin.m4thkidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

          if(DbHelper.getsInstance(getApplicationContext()).check_initialSound()) {

              DbHelper.getsInstance(getApplicationContext()).initialSound();

              String [] array1= {"Easy", "comparing", "multiple choice", "Sally has *BLANK* apples. She gets another *BLANK* from Bill, and *BLANK* from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
              }

              String [] array2= {"Easy", "counting", "multiple choice", "Sally has *BLANK* apples. She gets another *BLANK* from Bill, and *BLANK* from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array2);
              }

              String [] array3= {"Easy", "adding", "multiple choice", "Sally has *BLANK* apples. She gets another *BLANK* from Bill, and *BLANK* from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array3);
              }

              String [] array4= {"Easy", "subtraction", "multiple choice", "Sally has 2 apples. She gets another 1 from Bill, and two from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array4);
              }


              String [] array5= {"Easy", "times", "multiple choice", "*BLANK* x *BLANK* is how much?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array5);
              }


              String [] array6= {"Easy", "divide", "multiple choice", "Sally has 2 apples. She gets another 1 from Bill, and two from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);
              DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);

              for(int i = 0; i <= 19; i++) {
                  DbHelper.getsInstance(getApplicationContext()).addQuestion(array6);
              }



          }

        finish();
    }
}
