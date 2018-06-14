/*
* Klasa RealmAdapter służy do przesyłania danych pobranych z bazy realm do aktywności Favorites
* Pobiera ona nazwę albumu poczym przesyła ją do elementu ArrayList
* */
//Dodano - Kamil w57449


package com.example.mediaapp.db;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmHelper {

    Realm realm;
    public RealmHelper(Realm realm){
        this.realm = realm;
    }


    public ArrayList<String> retrieve(){
        ArrayList<String> favoriteAlbums=new ArrayList<>();
        RealmResults<Favorite> favorites = realm
                .where(Favorite.class )
                .sort( "date", Sort.DESCENDING )
                .findAll();
    for(Favorite s:favorites){
        favoriteAlbums.add(s.getAlbum());

    }
return favoriteAlbums;
    }



}
