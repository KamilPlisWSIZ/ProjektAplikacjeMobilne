/*
* Klasa VideoPlayer odpowiada za odtwarzanie pliku wideo
* */

package com.example.mediaapp;


import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;
import android.widget.MediaController;


public class VideoPlayer extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_player);

        //Przesyłanie pliku wideo do elementu VideoView
        VideoView videoView = (VideoView) findViewById(R.id.video_view);


        //Przypisanie adresu pliku do zmiennej
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.samplevideo;

        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }



        Uri uri = Uri.parse(videoPath);
        //Uruchomienie odtwarzania
        videoView.setVideoURI(uri);



        MediaController mediaController = new MediaController(this);

        //Rozszerzenie funkcjonalności o opcję pełnego ekranu
        mediaController = new FullScreenMediaController(this);
        mediaController.setAnchorView(videoView);


        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
