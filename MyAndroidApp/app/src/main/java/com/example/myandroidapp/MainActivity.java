package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSettings, btnStart, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = findViewById(R.id.btnSettings);
        btnStart = findViewById(R.id.btnStart);
        btnExit = findViewById(R.id.btnExit);

        btnSettings.setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show());

        btnStart.setOnClickListener(v -> {
            // Start QuizSelectionActivity with animation
            Intent intent = new Intent(MainActivity.this, QuizSelectionActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnExit.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Exit clicked", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
