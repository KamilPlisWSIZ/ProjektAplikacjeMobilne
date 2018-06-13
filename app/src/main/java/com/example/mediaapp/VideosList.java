/*
* Klasa VideosList ma za zadanie pobrać wszystkie pliki video zgromadzone w pamięci
* i przekazać je do elementu ListView
* */


package com.example.mediaapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VideosList extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST =1;

    ArrayList<String> arrayList;

    ListView listView;

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(ContextCompat.checkSelfPermission(VideosList.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(VideosList.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){

                ActivityCompat.requestPermissions(VideosList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
            else{
                ActivityCompat.requestPermissions(VideosList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }

        }
        else{
            doStuff();
        }
    }

    //Przesyła pobrane w getVideos zmienne do elementu ListView
    public void doStuff(){
        listView = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        getVideos();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

            }
        });
    }



    //pobiera pliki video i przypisuje ich tytuł i rozmiar do odpowiednich zmiennych
    public void getVideos(){
        ContentResolver contentResolver = getContentResolver();
        Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor videoCursor = contentResolver.query(videoUri, null, null, null,null);

        if(videoCursor != null && videoCursor.moveToFirst()) {
            int videoTitle = videoCursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            int videoSize = videoCursor.getColumnIndex(MediaStore.Video.Media.SIZE);

            do{
                String currentTitle = "Title: " + videoCursor.getString(videoTitle);
                String currentSize =  "Size: " + videoCursor.getString(videoSize);
                arrayList.add(currentTitle + "\n"+ currentSize);
            } while (videoCursor.moveToNext());

        }
    }
//Metoda ta odpowiada za pozwolenia w aplikacji które mogą być udzielone lub też nie
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(VideosList.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted!",Toast.LENGTH_SHORT).show();

                        doStuff();
                    }
                }

                else{
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//obsługuje przycisk wstercz urządzenia

        return true;
    }



}




