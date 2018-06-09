package com.example.mediaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

private CardView videosIcon, vidslistIcon, favsIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videosIcon = (CardView) findViewById(R.id.videosicon);
        vidslistIcon = (CardView) findViewById(R.id.vidslisticon);
        favsIcon = (CardView) findViewById(R.id.favsicon);


        videosIcon.setOnClickListener(this);
        vidslistIcon.setOnClickListener(this);
        favsIcon.setOnClickListener(this);

    }


    //button methods
    public void openVideoPlayer(){
        Intent intent = new Intent(this, VideoPlayer.class );
        startActivity(intent);
    }

    public void openVideosList(){
        Intent intent = new Intent(this, VideosList.class );
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
Intent i;
        switch (v.getId()){
            case R.id.videosicon : i = new Intent(this,VideoPlayer.class);startActivity(i); break;
            case R.id.vidslisticon : i = new Intent(this,VideosList.class);startActivity(i); break;
            case R.id.favsicon : i = new Intent(this,Favorites.class);startActivity(i); break;
            default:break;
        }
    }
}
