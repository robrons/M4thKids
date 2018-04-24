package com.example.robin.m4thkidsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Sanchez on 3/23/2018.
 */

public class DbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "math4kids.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_SETTINGS = "Settings";
    private static final String COL01 = "Music_on";
    private static final String COL02 = "SoundEffects_on";

    private static final String TABLE_QUESTIONS = "Questions";
    private static final String COL11 = "Difficulty";
    private static final String COL12 = "Lesson";
    /*
    public static final String COL13  = "AnswerType";
    public static final String COL14 = "Question";
    public static final String COL15 = "Answer";
    public static final String COL16 = "is_Cat";
    public static final String COL17 = "is_Icecream";
    public static final String COL18 = "is_Dog";
    public static final String COL19 = "BackgroundColor";
    public static final String COL20 = "PossibleAnswers";
    */

    private SQLiteDatabase db;

    //Singleton design pattern

    //What the Singleton does is set up a private constructor so the class can't be instantiated anywhere from outside of this own class.
    //So, the class instantiates itself.
    //The design pattern is responsible for guaranteeing 3 things:
    //Only 1 instance of this class is ever instantiated at any time.
    //That single instance is synced up everywhere it's accessed from.
    //That singe instance is available everywhere throughout the package it's located in without having to be passed or even instantiated.
    private static DbHelper dbInstance;

    public static synchronized DbHelper getsInstance(Context context) //don't try to call this function directly yourself
    {
        if (dbInstance == null)
            dbInstance = new DbHelper(context.getApplicationContext());
        return dbInstance;
    }

    //CONSTRUCTOR
    //Because of how the Singleton works, an instance of this class will never be created from anywhere but in his class itself.
    //That's why this its constructor is set to private.
    //It's all...part of the plan."
    private DbHelper(Context context)  //whenever this is called, the database will be initialized.
    {
        super(context, DATABASE_NAME, null, DB_VERSION);
        db = getWritableDatabase();//This calls onCreate()
    }


    //System function, don't worry about this. It will never be called directly by the front end.
    @Override
    public void onCreate(SQLiteDatabase db) //onCreate is called when the database file does not exist or has not been created yet, and is called at first access.
    {
        String CREATE_TABLE_Settings = "CREATE TABLE IF NOT EXISTS Settings (Music_on INTEGER, SoundEffects_on INTEGER);";
        db.execSQL(CREATE_TABLE_Settings);

        String CREATE_TABLE_Questions = "CREATE TABLE IF NOT EXISTS Questions (ID INTEGER PRIMARY KEY AUTOINCREMENT, Difficulty TEXT, Lesson TEXT, AnswerType TEXT, Question TEXT, Answer TEXT, PossibleAnswers TEXT, BackgroundColor TEXT, is_Dog TEXT, is_Icecream TEXT, is_Cat TEXT);";
        db.execSQL(CREATE_TABLE_Questions);
    }


    //Also System function, don't worry about this. It will never be called directly by the front end.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }


    //This function is to set up the sound settings default values upon first launch.
    //So the splash page will need to include these 2 lines:
    //if(DbHelper.getsInstance(getApplicationContext()).check_initialSound())
    //      DbHelper.getsInstance(getApplicationContext()).initialSound();
    public void initialSound()
    {
        db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(COL01, 1);
        values.put(COL02, 1);

        db.insert(TABLE_SETTINGS, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public boolean check_initialSound()
    {
        db = this.getWritableDatabase();
        String count = "SELECT COUNT(*) FROM " + TABLE_SETTINGS;
        Cursor cur = db.rawQuery(count, null);
        cur.moveToFirst();
        int count2 = cur.getInt(0);

        db.close();
        cur.close();

        if (count2 == 0)
            return true;
        else
            return false;
    }


    //The goal of get_Music_and_Sound() is to return the status of those two sound settings. A 0 is off, a 1 means on.
    //I'm returning both at one time because they'd be the same function just written twice if done individually.
    //index 0 of the returned array is 0 if the music is off and 1 if it's on,
    //index 1 of the returned array is 0 if the sound effects are off and 1 if they're on.

    //EXAMPLE
    //int example_array [] = DbHelper.getsInstance(getApplicationContext()).get_Music_and_Sound();
    public int[] get_Music_and_Sound()
    {
        db = getReadableDatabase();
        Cursor cur = db.query("Settings", null, null, null, null, null, null, null);
        int array[] = {0, 0};
        if (cur.moveToFirst())
//        {
//            while ( !cur.isAfterLast() )
//            {
            array[0] = cur.getInt(0);
        array[1] = cur.getInt(1);
//                cur.moveToNext();
//            }
//        }
        db.close();
        cur.close();
        return array;

    }

    //This function, when called, will change the current music power from 1 to 0 or from 0 to 1.
    //DbHelper.getsInstance(getApplicationContext()).change_Music_Power();
    public void change_Music_Power()
    {
        db = this.getWritableDatabase();
        String[] column = {COL01};
        Cursor cur = db.query(TABLE_SETTINGS, column, null, null, null, null, null, null);
        int value = -1;
        if (cur.moveToFirst())
        {
            value = cur.getInt(0);
        }

        ContentValues values = new ContentValues();
        db.beginTransaction();
        if (value == 0)
        {
            values.put(COL01, 1);
            db.update(TABLE_SETTINGS, values, null, null);
        }
        if (value == 1)
        {
            values.put(COL01, 0);
            db.update(TABLE_SETTINGS, values, null, null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    //Same thing here, but with sound effects.
    //DbHelper.getsInstance(getApplicationContext()).change_SoundEffects_Power();
    public void change_SoundEffects_Power()
    {
        db = this.getWritableDatabase();
        String[] column = {COL02};
        Cursor cur = db.query(TABLE_SETTINGS, column, null, null, null, null, null, null);
        int value = -1;
        if (cur.moveToFirst())
        {
            value = cur.getInt(0);//this may or may not have to be a 1.
        }

        ContentValues values = new ContentValues();
        db.beginTransaction();
        if (value == 0)
        {
            values.put(COL02, 1);
            db.update(TABLE_SETTINGS, values, null, null);
        }
        if (value == 1)
        {
            values.put(COL02, 0);
            db.update(TABLE_SETTINGS, values, null, null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    //{Difficulty, Lesson, AnswerType, Question, Answer, PossibleAnswers, BackgroundColor, is_Dog, is_Icecream, is_Cat}
    //addQuestion() will take in an array of size 10. The ordering of indeces must match with the above schema.
    //The goal of this function is to add a question into the database.
    //This won't be something that ever happens while the user is using the app, the questions must all be hard coded in upon first launch of the app.

    //EXAMPLE
    //String [] array1= {"Easy", "Addition", "multiple choice", "Sally has 2 apples. She gets another 1 from Bill, and two from Kate. How many does she have now?", "5", "4, 3, 5, 0", "green", "no", "Yes", "no"};
    //DbHelper.getsInstance(getApplicationContext()).addQuestion(array1);
    public void addQuestion(String array[])
    {
        db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put("Difficulty", array[0]);
        values.put("Lesson", array[1]);
        values.put("AnswerType", array[2]);
        values.put("Question", array[3]);
        values.put("Answer", array[4]);
        values.put("PossibleAnswers", array[5]);
        values.put("BackgroundColor", array[6]);
        values.put("is_Dog", array[7]);
        values.put("is_Icecream", array[8]);
        values.put("is_Cat", array[9]);

        db.insert(TABLE_QUESTIONS, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }


    //The returned array will be of size 10, just like the inputted array for addQuestion().
    //EXAMPLE
    //int some_ID = 4;
    //String [] question1 = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withID(some_ID);
    public String [] grabQuestion_withID(int question_ID)
    {
        String [] question = new String[10];

        db = getReadableDatabase();
        String [] columns = {"Difficulty", "Lesson", "AnswerType", "Question", "Answer", "PossibleAnswers", "BackgroundColor", "is_Dog", "is_Icecream", "is_Cat"};

        Cursor cur = db.query(TABLE_QUESTIONS, columns, "ID = " + question_ID, null, null, null, null, null);
        List<String> theRow = new ArrayList<String>();
        int x = 0;
        if (cur.moveToFirst())
        {
            String temp;

            do {
                for (int i = 0; i < columns.length; i++) {
                    temp = cur.getString(i);
                    theRow.add(temp);
                }
            } while (cur.moveToNext());

        }

        question = theRow.toArray(new String[0]);

        cur.close();
        db.close();
        return question;
    }

    //Given a difficulty as a string, this function returns a List object that's populated with other list objects of strings.
    //The strings in each list are 1 per attribute: "Lesson", "AnswerType", "Question", "Answer", "PossibleAnswers",
    //          "BackgroundColor", "is_Dog", "is_Icecream", "is_Cat"
    //Each list in the top list is a single question's data.
    //EXAMPLE
    //String some_difficulty = "Easy";
    //List<List<String>> questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withDifficulty(some_difficulty);
    public List<List<String>> grabQuestion_withDifficulty(String difficulty)
    {
        List<List<String>> questions = new ArrayList<>();

        db = getReadableDatabase();
        String [] columns = {"Lesson", "AnswerType", "Question", "Answer", "PossibleAnswers", "BackgroundColor", "is_Dog", "is_Icecream", "is_Cat"};

        Cursor cur = db.query(TABLE_QUESTIONS, columns, COL11 + " = \"" + difficulty+ "\"", null, null, null, null, null);
        List<String> theRow = new ArrayList<String>();
        int x = 0;

            if (cur.moveToFirst())
            {
                String temp;

                do
                {
                    for (int i = 0; i < columns.length; i++)
                    {
                        temp = cur.getString(i);
                        theRow.add(temp);
                    }
                    questions.add(theRow);
                } while (cur.moveToNext());

            }

        cur.close();
        db.close();
        return questions;
    }


    //Given a lesson as a string, this function returns a List object that's populated with other list objects of strings.
    //The strings in each list are 1 per attribute: "AnswerType", "Question", "Answer", "PossibleAnswers",
    //          "BackgroundColor", "is_Dog", "is_Icecream", "is_Cat"
    //Each list in the top list is a single question's data.
    //EXAMPLE
    //String some_difficulty = "Easy";
    //List<List<String>> questionSet = DbHelper.getsInstance(getApplicationContext()).grabQuestion_withDifficulty(some_difficulty);
    public List<List<String>> grabQuestion_withLesson(String lesson)
    {
        List<List<String>> questions = new ArrayList<>();

        db = getReadableDatabase();
        String [] columns = {"AnswerType", "Question", "Answer", "PossibleAnswers", "BackgroundColor", "is_Dog", "is_Icecream", "is_Cat"};

        Cursor cur = db.query(TABLE_QUESTIONS, columns, COL12 + " = \"" + lesson+ "\"", null, null, null, null, null);
        List<String> theRow = new ArrayList<String>();
        int x = 0;

        if (cur.moveToFirst())
        {
            String temp;

            do
            {
                for (int i = 0; i < columns.length; i++)
                {
                    temp = cur.getString(i);
                    theRow.add(temp);
                }
                questions.add(theRow);
            } while (cur.moveToNext());

        }

        cur.close();
        db.close();
        return questions;
    }
    
    //DatabaseHelper.getsInstance(getApplicationContext()).death();
    public void death(Context context)
    {
        context.deleteDatabase(DATABASE_NAME);
    }

}
