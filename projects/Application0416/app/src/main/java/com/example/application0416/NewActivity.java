package com.example.application0416;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {

    TextView textTitle;
    EditText resultURL, resultPhone;
    Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        textTitle = findViewById(R.id.textTitle);
        resultURL = findViewById(R.id.resultURL);
        resultPhone = findViewById(R.id.resultPhone);
        buttonReturn = findViewById(R.id.buttonReturn);

        // 데이터 수신 (이름 표시)
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            textTitle.setText(name);
        }

        // 돌아가기 → URL & Phone 값을 MainActivity로 전달
        buttonReturn.setOnClickListener(view -> {
            String url = resultURL.getText().toString();
            String phone = resultPhone.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("url", url);
            resultIntent.putExtra("phone", phone);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
