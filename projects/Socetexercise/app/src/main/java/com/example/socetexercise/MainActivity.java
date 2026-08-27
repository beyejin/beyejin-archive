package com.example.socetexercise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText input01;
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = findViewById(R.id.editText01);
        button01 = findViewById(R.id.button01);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                ConnectThread thread = new ConnectThread(addr);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr) {
            hostname = addr;
        }

        public void run() {
            try {
                int port = 7777;
                Socket sock = new Socket(hostname, port);

                InputStream in = sock.getInputStream();
                DataInputStream dis = new DataInputStream(in);

                Log.d("MainActivity", "서버에서 받은 메시지 : " + dis.readUTF());

                dis.close();
                sock.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
