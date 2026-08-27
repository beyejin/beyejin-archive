package com.example.myactivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    public MyService() {
        // 기본 생성자
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스가 시작될 때 호출됨
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        // 서비스가 강제 종료되었을 때 재시작하도록 설정
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // 서비스가 종료될 때 호출됨
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 바인딩용 서비스가 아닐 경우 사용하지 않음
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
