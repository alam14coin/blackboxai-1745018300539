package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

        int[] buttonIds = {
            R.id.btnQuiz1, R.id.btnQuiz2, R.id.btnQuiz3,
            R.id.btnQuiz4, R.id.btnQuiz5, R.id.btnQuiz6,
            R.id.btnQuiz7, R.id.btnQuiz8, R.id.btnQuiz9,
            R.id.btnQuiz10, R.id.btnQuiz11, R.id.btnQuiz12
        };

        for (int i = 0; i < buttonIds.length; i++) {
            int quizNumber = i + 1;
            Button btn = findViewById(buttonIds[i]);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(QuizSelectionActivity.this, QuizQuestionActivity.class);
                intent.putExtra("quizNumber", quizNumber);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }
    }
}
