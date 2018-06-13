/*
*
* Klasa służąca do poszukiwania najpopularniejszych utworów danego wykonawcy
* */


package com.example.mediaapp.searchtrack;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediaapp.R;
import com.example.mediaapp.api.ApiService;
import com.example.mediaapp.api.SearchTrack;
import com.example.mediaapp.api.SearchTracks;
import com.example.mediaapp.searchtrack.SearchTrackAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTrackActivity extends AppCompatActivity {
    EditText etQuery;
    RecyclerView rvList;
    SharedPreferences sharedPreferences;

    List<SearchTrack> tracks = new ArrayList<>(0);// modyfikacja metody onCreate

//przeładowujemy metodę onCreate() gdzie za pomocą funkcji setContentView(), ładujemy wcześniej przygotowany plik layoutem.
    @Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

setContentView(R.layout.activity_search_track);


 getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    sharedPreferences = getPreferences(MODE_PRIVATE);



    etQuery=findViewById(R.id.etQuery);








        try{
        etQuery.setText(sharedPreferences.getString("query", null));

    } catch (ClassCastException e) {
        Log.e("TAG", "blad", e);
    }

        String artist = sharedPreferences.getString("query", null);
                etQuery.setText(artist);

        Button bSearch = findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = etQuery.getText().toString();
                rememberQuery(query);
                searchTracks(query);
            }
        });

        rvList = findViewById(R.id.rvList);

        SearchTrackAdapter searchAlbumAdapter = new SearchTrackAdapter(tracks);
        rvList.setAdapter(searchAlbumAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);


    }

    private void rememberQuery(String query){
        SharedPreferences.Editor editor = sharedPreferences.edit();//klasa ta umożliwia nam modyfikację wpisów
        editor.putString("query",query);
        editor.apply();
        etQuery.setText(sharedPreferences.getString("query", null));//pobranie danych dla klucza query, metoda gerString
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//Wywołanie metody, przycisk wtecz urządzenia
        return true ;// zwraca true, ponieważ zwrócona musi być wartość boolean
    }
//metoda updateList zostanie wywołana w metodzie onResponse() w momencie otrzymania danych z API
    private void updateList(SearchTracks searchTracks){
        tracks.clear();
        tracks.addAll(searchTracks.track);

        rvList.getAdapter().notifyDataSetChanged();
    }

//metoda ta zostania wywołana po przez kliknięcie na przycisk, przerywanie następuję po przez użycie instrukcji return
    private void searchTracks(String query) {
        getSupportActionBar().setSubtitle(query);

        if (query == null || query.isEmpty()) {
            Toast.makeText(this, "Pusta fraza", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<SearchTracks> searchTracksCall = ApiService.getService().searchTracks(query);
        searchTracksCall.enqueue(new Callback<SearchTracks>() {
            @Override
            public void onResponse(@NonNull Call<SearchTracks> call, @NonNull Response<SearchTracks> response) {
                SearchTracks searchTracks = response.body();

                if (searchTracks == null || searchTracks.track == null || searchTracks.track.isEmpty()) {
                    Toast.makeText(SearchTrackActivity.this, "No items!", Toast.LENGTH_SHORT).show();
                    return;
                }



                Toast.makeText(SearchTrackActivity.this, "Found " + searchTracks.track.size() + " items", Toast.LENGTH_SHORT).show();
                updateList(searchTracks);


            }


// dzięki tej aktywności możemy otrzymać informacje o stanie pobieranych danych
            @Override
            public void onFailure(@NonNull Call<SearchTracks> call, Throwable t) {
                Toast.makeText(SearchTrackActivity.this, "Błąd pobierania danych: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}