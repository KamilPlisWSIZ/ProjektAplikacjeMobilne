package com.example.mediaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.mediaapp.favorites.FavoritesActivity;
import com.example.mediaapp.topsongs.TopSongsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

private CardView videosIcon, vidslistIcon, favoritesIcon, musicplayerIcon, topalbumsIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videosIcon = (CardView) findViewById(R.id.videosicon);
        vidslistIcon = (CardView) findViewById(R.id.vidslisticon);
        topalbumsIcon = (CardView) findViewById(R.id.topalbumsicon);
        favoritesIcon = (CardView) findViewById(R.id.favoritesicon);
        musicplayerIcon = (CardView) findViewById(R.id.musicplayericon);


        videosIcon.setOnClickListener(this);
        vidslistIcon.setOnClickListener(this);
        topalbumsIcon.setOnClickListener(this);
        musicplayerIcon.setOnClickListener(this);
        favoritesIcon.setOnClickListener(this);
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
            case R.id.topalbumsicon : i = new Intent(this,TopSongsActivity.class);startActivity(i); break;
            case R.id.musicplayericon : i = new Intent(this,MusicPlayer.class);startActivity(i); break;
            case R.id.favoritesicon : i = new Intent(this,FavoritesActivity.class);startActivity(i); break;
            default:break;
        }
    }
}
