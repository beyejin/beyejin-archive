package com.example.videoexcercise;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String VIDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(VIDEO_URL));

        Button startBtn = findViewById(R.id.startBtn);
        Button volumeBtn = findViewById(R.id.volumeBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_SHOW_UI);
            }
        });
    }
}
