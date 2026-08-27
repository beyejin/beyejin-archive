package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView timerView;
    private Button startButton, pauseButton, resetButton, recordButton, moreButton;
    private LinearLayout controlButtonsWrapper;

    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean isRunning = false;
    private long startTime = 0L;
    private long pausedTime = 0L;
    private Thread timerThread;

    private ArrayList<String> records = new ArrayList<>();

    private String getTimeString(long millis) {
        long centis = (millis / 10) % 100;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = millis / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, centis);
    }

    private void startTimer() {
        if (isRunning) return;
        isRunning = true;
        startTime = System.currentTimeMillis() - pausedTime;
        timerThread = new Thread(() -> {
            while (isRunning) {
                long elapsed = System.currentTimeMillis() - startTime;
                handler.post(() -> timerView.setText(getTimeString(elapsed)));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    private void pauseTimer() {
        if (!isRunning) return;
        isRunning = false;
        pausedTime = System.currentTimeMillis() - startTime;
    }

    private void resetTimer() {
        isRunning = false;
        pausedTime = 0L;
        handler.post(() -> timerView.setText("00:00:00:00"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignment-CH7");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timerView = findViewById(R.id.textView_timer);
        startButton = findViewById(R.id.button_start);
        pauseButton = findViewById(R.id.button_pause);
        resetButton = findViewById(R.id.button_reset);
        recordButton = findViewById(R.id.button_record);
        moreButton = findViewById(R.id.button_more);
        controlButtonsWrapper = findViewById(R.id.control_buttons_wrapper);

        startButton.setOnClickListener(v -> {
            startTimer();
            startButton.setVisibility(View.GONE);
            controlButtonsWrapper.setVisibility(View.VISIBLE);
            pauseButton.setText("일시정지");
        });

        pauseButton.setOnClickListener(v -> {
            if (isRunning) {
                pauseTimer();
                pauseButton.setText("다시시작");
            } else {
                startTimer();
                pauseButton.setText("일시정지");
            }
        });

        resetButton.setOnClickListener(v -> {
            resetTimer();
            controlButtonsWrapper.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
            pauseButton.setText("일시정지");
            records.clear();
        });

        recordButton.setOnClickListener(v -> {
            String currentTime = timerView.getText().toString();
            records.add(currentTime);
        });

        moreButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            intent.putStringArrayListExtra("recordList", records);
            startActivity(intent);
        });
    }
}
