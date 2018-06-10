package com.example.mediaapp.db;
import java.util.Date;
import io.realm.RealmObject;


public class Favorite extends RealmObject {
    private String album ;
    private String artist ;
    private int albumId ;
    private Date date ;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) { this.album = album;
    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) { this.artist = artist;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) { this.albumId = albumId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}