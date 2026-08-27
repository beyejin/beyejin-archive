package com.example.mylogin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView labelUserName;
    EditText txtUserName;
    Button btnBegin;
    Context context;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        // 레이아웃에 있는 위젯들과 연결
        labelUserName = findViewById(R.id.textView1);
        txtUserName = findViewById(R.id.txtUserName);
        btnBegin = findViewById(R.id.button1);

        // 버튼 클릭 이벤트 처리
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString();

                if (userName.equals("Younhyun Jung")) {
                    labelUserName.setText("OK, Please wait...");
                    Toast.makeText(context, "Hi!, Prof. " + userName, duration).show();
                } else {
                    Toast.makeText(context, userName + " is not a valid User", duration).show();
                }
            }
        });
    }
}
