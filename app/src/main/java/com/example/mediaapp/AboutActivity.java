/*Klasa AboutActivity wyswietla informacje o autorach*/

package com.example.mediaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.mediaapp.favorites.FavoritesActivity;
import com.example.mediaapp.searchtrack.SearchTrackActivity;
import com.example.mediaapp.topsongs.TopSongsActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



    }
}

