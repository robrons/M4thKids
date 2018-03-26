package com.example.robin.m4thkidsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;


/**
 * Created by David Sanchez on 3/23/2018.
 */

public class DbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "math4kids.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_SETTINGS = "Settings";
    public static final String COL01 = "Music_on";
    public static final String COL02 = "SoundEffects_on";

    public static final String TABLE_QUESTIONS = "Questions";
    public static final String COL11 = "Difficulty";
    public static final String COL12 = "Lesson";
    public static final String COL13  = "AnswerType";
    public static final String COL14 = "Question";
    public static final String COL15 = "Answer";
    public static final String COL16 = "is_Cat";
    public static final String COL17 = "is_Icecream";
    public static final String COL18 = "is_Dog";
    public static final String COL19 = "BackgroundColor";
    public static final String COL20 = "PossibleAnswers";


    SQLiteDatabase db;

    //Singleton design pattern
    private static DbHelper dbInstance;
    public static synchronized DbHelper getsInstance(Context context)
    {
        if (dbInstance == null)
            dbInstance = new DbHelper(context.getApplicationContext());
        return dbInstance;
    }

    //CONSTRUCTOR
    private DbHelper(Context context)  //whenever this is called, the database will be initialized.
    {
        super(context, DATABASE_NAME, null, DB_VERSION);
        db = getWritableDatabase();
//        tablesInfo = restoreDBState();

    }

    @Override
    public void onCreate(SQLiteDatabase db) //onCreate is called when the database file does not exist or has not been created yet.
    {
        String CREATE_TABLE_Settings = "CREATE TABLE IF NOT EXISTS Settings (\"Music_on INTEGER, SoundEffects_on INTEGER)\";";
        db.execSQL(CREATE_TABLE_Settings);

        String CREATE_TABLE_Questions = "CREATE TABLE IF NOT EXISTS Questions (\"Difficulty TEXT, Lesson TEXT, AnswerType TEXT, Question TEXT, Answer TEXT, PossibleAnswers TEXT, BackgroundColor TEXT, is_Dog TEXT, is_Icecream TEXT, is_Cat TEXT)\";";
        db.execSQL(CREATE_TABLE_Questions);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

//  DbHelper.getsInstance(getApplicationContext()).get_Music_and_Sound();
    public int [] get_Music_and_Sound()
    {

        db = getReadableDatabase();
        Cursor cur = db.query("Settings", null, null, null, null, null, null, null);
        int array[] = {0,0};
//        if (cur.moveToFirst())
//        {
//            while ( !cur.isAfterLast() )
//            {
                array[0] = cur.getInt(0);//*NOT SURE IF COLUMNS START AT 0 OR 1*
                array[1] = cur.getInt(1);
//                cur.moveToNext();
//            }
//        }
        db.close();
        cur.close();
        return array;

    }



}
