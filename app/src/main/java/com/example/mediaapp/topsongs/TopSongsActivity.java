
package com.example.mediaapp.topsongs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mediaapp.AboutActivity;
import com.example.mediaapp.R;
import com.example.mediaapp.api.ApiService;

import com.example.mediaapp.api.TrendingList;
import com.example.mediaapp.api.TrendingAlbum;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSongsActivity extends AppCompatActivity {
    Button bAbout;


    RecyclerView rvList;
    List<TrendingAlbum> trendingAlbums = new ArrayList<>( 0 );
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //górny pasek aplikacji

        rvList = findViewById(R.id.rvList);
        Button bAbout = (Button)findViewById(R.id.bAbout);

        bAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TopSongsActivity.this, AboutActivity.class));
            }
        });


        TopSongsAdapter topSongsAdapter = new TopSongsAdapter(trendingAlbums);
                rvList .setAdapter(topSongsAdapter);

                       LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);

                       rvList .setLayoutManager(linearLayoutManager);





        Call<TrendingList> trendingListCall = ApiService.getService().getTrendingList("us",
                "itunes", "albums");
        trendingListCall.enqueue(new Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull Call<TrendingList> call, @NonNull
                    Response<TrendingList> response) {
                TrendingList trendingList = response.body();

                if (trendingList != null ) {
                                        TopSongsActivity.this.trendingAlbums.clear();
                                       TopSongsActivity.this.trendingAlbums.addAll(trendingList.trending );
                                       TopSongsActivity.this.rvList.getAdapter().notifyDataSetChanged();
                                    }


            }

            @Override
            public void onFailure(@NonNull Call<TrendingList> call, Throwable t) {
                Toast.makeText(TopSongsActivity.this, "Blad pobierania danych: " +
                        t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true ;
    }
}


