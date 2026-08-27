package com.example.assignment2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    private Button backButton;
    private TextView titleText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        backButton = findViewById(R.id.button_back);
        titleText = findViewById(R.id.text_title);
        listView = findViewById(R.id.list_records);

        ArrayList<String> records = getIntent().getStringArrayListExtra("recordList");
        if (records == null) {
            records = new ArrayList<>();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, records
        );
        listView.setAdapter(adapter);

        backButton.setOnClickListener(v -> finish());
    }
}
