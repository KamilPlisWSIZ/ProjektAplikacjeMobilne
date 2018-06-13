package com.example.mediaapp;




import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
public class MediaPlayer extends Application {
// klasa obsługująca aplikację
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); // inicljalizacja biblioteki Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().schemaVersion(1).deleteRealmIfMigrationNeeded().build();//wersja bazy danych = 1, czyści baze danych kiedy wymagana jest migracja
        Realm.compactRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}

