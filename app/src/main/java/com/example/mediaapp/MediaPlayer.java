package com.example.mediaapp;




import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
public class MediaPlayer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().schemaVersion(1).deleteRealmIfMigrationNeeded().build();
        Realm.compactRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}

