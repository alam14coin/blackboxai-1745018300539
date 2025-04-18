package com.example.myandroidapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUIZ_NUMBER = "quiz_number";
    public static final String COLUMN_QUESTION_NUMBER = "question_number";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_ANSWER1 = "answer1";
    public static final String COLUMN_ANSWER2 = "answer2";
    public static final String COLUMN_ANSWER3 = "answer3";
    public static final String COLUMN_ANSWER4 = "answer4";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUIZ_NUMBER + " INTEGER,"
                + COLUMN_QUESTION_NUMBER + " INTEGER,"
                + COLUMN_QUESTION_TEXT + " TEXT,"
                + COLUMN_ANSWER1 + " TEXT,"
                + COLUMN_ANSWER2 + " TEXT,"
                + COLUMN_ANSWER3 + " TEXT,"
                + COLUMN_ANSWER4 + " TEXT,"
                + COLUMN_CORRECT_ANSWER + " INTEGER"
                + ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);

        // Insert sample data for quiz 1, question 1
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" +
                COLUMN_QUIZ_NUMBER + ", " +
                COLUMN_QUESTION_NUMBER + ", " +
                COLUMN_QUESTION_TEXT + ", " +
                COLUMN_ANSWER1 + ", " +
                COLUMN_ANSWER2 + ", " +
                COLUMN_ANSWER3 + ", " +
                COLUMN_ANSWER4 + ", " +
                COLUMN_CORRECT_ANSWER + ") VALUES (1, 1, 'the question here??', 'chose 1', 'chose 2', 'chose 3', 'chose 3', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    public Cursor getQuestion(int quizNumber, int questionNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE "
                + COLUMN_QUIZ_NUMBER + "=? AND " + COLUMN_QUESTION_NUMBER + "=?";
        return db.rawQuery(query, new String[]{String.valueOf(quizNumber), String.valueOf(questionNumber)});
    }
}
