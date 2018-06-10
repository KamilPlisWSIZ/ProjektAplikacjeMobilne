package com.example.mediaapp.topsongs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mediaapp.R;

public class TopSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true ;
        }
}
