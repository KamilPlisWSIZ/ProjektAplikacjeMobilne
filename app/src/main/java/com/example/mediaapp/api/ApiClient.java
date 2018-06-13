//Klasa ApiClient przechowuje informacje na temat podstron z których będziemy pobierać dane w formacie JSON
package com.example.mediaapp.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    //Pobieranie najpopularniejszych albumów w ostatnim czasie
    @GET( "trending.php" )
Call<TrendingList> getTrendingList(@Query( "country" ) String country, @Query ( "type" )
String type, @Query ( "format" ) String format);

    //Pobieranie informacji szegółowych o albumie
    @GET("album.php")
   Call<Albums>getAlbum(@Query("m")int albumId);

    //Pobieranie top 10 utworów podanego wykonawcy
    @GET("track-top10.php")
    Call<SearchTracks> searchTracks(@Query("s") String artist);


}