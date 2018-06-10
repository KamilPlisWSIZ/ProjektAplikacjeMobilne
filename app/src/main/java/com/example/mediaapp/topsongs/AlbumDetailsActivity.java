package com.example.mediaapp.topsongs;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.mediaapp.api.Album;

import com.example.mediaapp.R;
import com.example.mediaapp.api.ApiService;
import com.example.mediaapp.api.Albums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDetailsActivity extends AppCompatActivity {


  public static final String ALBUM = "album";
   public static final String ARTIST = "artist";
    public static final String ALBUM_ID = "album_id";




    @Override
  protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_album_details);

                       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


                            Intent intent = getIntent();
           String album = intent.getStringExtra(ALBUM);
            String artist = intent.getStringExtra(ARTIST);
        int albumId = intent.getIntExtra(ALBUM_ID , 0 );


                        Toast.makeText(this, album, Toast.LENGTH_SHORT).show();

                        getSupportActionBar().setTitle(album);
                getSupportActionBar().setSubtitle(artist);

                        ApiService.getService ().getAlbum(albumId).enqueue( new Callback<Albums>() {
            @Override
           public void onResponse(@NonNull Call<Albums> call, @NonNull Response<Albums>
                   response) {
                Albums albums = response.body();
                               if (albums != null && albums. album .size()>0 ) {
                                      showData(albums. album .get(0));
                                   }
                                    }
            @Override
            public void onFailure( @NonNull Call<Albums> call, @NonNull Throwable t) {
                                        Toast. makeText (
                                                        AlbumDetailsActivity. this ,
                                                        "Błąd pobierania danych: " + t.getLocalizedMessage(),
                                                        Toast. LENGTH_SHORT
                                                        ).show();
                                    }
        });


                            }

    private void showData(Album album) {
                TextView tvAlbum = findViewById(R.id.tvAlbum );
                 TextView tvArtist = findViewById(R.id.tvArtist );


                tvAlbum.setText(album.strAlbum );
                tvArtist.setText(album.strArtist );



               if (album. strAlbumThumb != null && !album.strAlbumThumb.isEmpty()) {
                        ImageView ivThumb = findViewById(R.id.ivThumb );
                        Glide. with ( this ).load(album.strAlbumThumb ).into(ivThumb);
                   }


                            }

            @Override
    public boolean onSupportNavigateUp() {
                onBackPressed();
                return true ;
            }
}