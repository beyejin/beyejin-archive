package com.example.application0416;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textNameDisplay;
    EditText editName, editDept, editID;
    EditText resultURL, resultPhone;
    Button buttonLogin, buttonWeb, buttonCall;

    static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 텍스트뷰 고정: 고정된 이름 (변경하지 않음)
        textNameDisplay = findViewById(R.id.textNameDisplay);

        // 입력 필드
        editName = findViewById(R.id.editName);
        editDept = findViewById(R.id.editDept);
        editID = findViewById(R.id.editID);

        // 결과 출력 필드
        resultURL = findViewById(R.id.resultURL);
        resultPhone = findViewById(R.id.resultPhone);

        // 버튼
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonWeb = findViewById(R.id.buttonWeb);
        buttonCall = findViewById(R.id.buttonCall);

        // 로그인 버튼 클릭
        buttonLogin.setOnClickListener(view -> {
            String name = editName.getText().toString();
            String dept = editDept.getText().toString();
            String id = editID.getText().toString();

            // 간단한 유효성 검사
            if (name.isEmpty() || dept.isEmpty() || id.isEmpty()) {
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this,
                    "Student Info : " + name + ", " + dept + ", " + id,
                    Toast.LENGTH_LONG).show();

            // 이름은 화면에는 반영하지 않음 (요구사항 반영)
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
        });

        // 웹 접속 버튼
        buttonWeb.setOnClickListener(view -> {
            String url = resultURL.getText().toString();
            if (url.isEmpty()) {
                Toast.makeText(this, "URL을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // 전화 연결 버튼
        buttonCall.setOnClickListener(view -> {
            String phone = resultPhone.getText().toString();
            if (phone.isEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // tel: prefix 확인 및 자동 추가
            if (!phone.startsWith("tel:")) {
                phone = "tel:" + phone;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String url = data.getStringExtra("url");
            String phone = data.getStringExtra("phone");

            resultURL.setText(url);
            resultPhone.setText(phone);
        }
    }
}
