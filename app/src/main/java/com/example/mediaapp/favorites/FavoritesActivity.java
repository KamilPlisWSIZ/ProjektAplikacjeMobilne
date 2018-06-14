/*
*Klasa Favorites Activity służy do przesyłania elementów które pojawią się na ekranie
 * użytkownika po wejściu do aktywności ulubionych
  * Inicjuje ona pobieranie danych z klasy RealmHelper*/
//Dodano - Kamil w57449

package com.example.mediaapp.favorites;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediaapp.MainActivity;
import com.example.mediaapp.R;
import com.example.mediaapp.db.Favorite;
import com.example.mediaapp.db.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
public class FavoritesActivity extends AppCompatActivity {

android.support.v7.widget.RecyclerView rvList;
Realm realm;
ArrayList<String> favorites;
//ArrayList <String> favorites = new ArrayList<String>();
    ArrayAdapter adapter;
ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //Przesłanie pobranych z bazy danych elementów do widoku listy

        lv = (ListView) findViewById(R.id.lv);
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);
        RealmHelper helper = new RealmHelper(realm);
        favorites=helper.retrieve();

        //Bindowanie
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,favorites);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FavoritesActivity.this,favorites.get(position),Toast.LENGTH_SHORT);
            }
        });






    }
}