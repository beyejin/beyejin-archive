package com.example.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.EdgeToEdge;

public class MainActivity extends AppCompatActivity {

    String tag = "LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 엣지 투 엣지 화면 적용
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 인셋 적용: 상태바 영역 피해서 UI 보이게 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(tag, "In the onCreate() event");

        // 서비스 시작 버튼
        Button btnStart = findViewById(R.id.btnStartService);
        btnStart.setOnClickListener(v ->
                startService(new Intent(getApplicationContext(), MyService.class)));

        // 서비스 종료 버튼
        Button btnStop = findViewById(R.id.btnStopService);
        btnStop.setOnClickListener(v ->
                stopService(new Intent(getApplicationContext(), MyService.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "In the onResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "In the onStop() event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }
}
