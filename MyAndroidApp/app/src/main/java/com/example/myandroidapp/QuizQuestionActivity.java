package com.example.myandroidapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizQuestionActivity extends AppCompatActivity {

    private TextView questionNumberText, timerText, questionText;
    private Button answer1, answer2, answer3, answer4;

    private CountDownTimer countDownTimer;
    private static final long TIMER_DURATION = 15000; // 15 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        questionNumberText = findViewById(R.id.questionNumberText);
        timerText = findViewById(R.id.timerText);
        questionText = findViewById(R.id.questionText);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        int quizNumber = getIntent().getIntExtra("quizNumber", 1);
        int questionNumber = 1; // For now, always question 1, can be extended

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        try (android.database.Cursor cursor = dbHelper.getQuestion(quizNumber, questionNumber)) {
            if (cursor != null && cursor.moveToFirst()) {
                questionNumberText.setText("Question number " + questionNumber);
                questionText.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUESTION_TEXT)));
                answer1.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ANSWER1)));
                answer2.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ANSWER2)));
                answer3.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ANSWER3)));
                answer4.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ANSWER4)));
            } else {
                questionNumberText.setText("Question not found");
            }
        }

        startTimer();

        answer1.setOnClickListener(v -> showAnswerSelected(answer1.getText().toString()));
        answer2.setOnClickListener(v -> showAnswerSelected(answer2.getText().toString()));
        answer3.setOnClickListener(v -> showAnswerSelected(answer3.getText().toString()));
        answer4.setOnClickListener(v -> showAnswerSelected(answer4.getText().toString()));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                timerText.setText("0s");
                Toast.makeText(QuizQuestionActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                // TODO: Move to next question or end quiz
            }
        }.start();
    }

    private void showAnswerSelected(String answer) {
        Toast.makeText(this, "Selected: " + answer, Toast.LENGTH_SHORT).show();
        // TODO: Check answer correctness, load next question, etc.
        // For demo, just finish activity with fade out animation
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
