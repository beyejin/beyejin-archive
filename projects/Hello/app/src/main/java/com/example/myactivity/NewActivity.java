package com.example.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
// 필요 시: import androidx.core.view.WindowCompat;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // 버전에 따라 사용
        setContentView(R.layout.activity_new);

        // 상태바 등 시스템 창 여백 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_new), (v, insets) -> {
            WindowInsetsCompat systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 버튼 눌렀을 때 액티비티 종료
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "돌아가기버튼이 눌렸어요", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
