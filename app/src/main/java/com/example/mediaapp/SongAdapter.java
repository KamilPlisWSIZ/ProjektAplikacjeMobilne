/*
*
* Klasa SongAdapter służy do przesyłania do aktywności informacji na temat pobranych utworów
* */
//Dodano Rafał w56998
package com.example.mediaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private ArrayList<SongInfo> _songs = new ArrayList<SongInfo>();
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public SongAdapter(Context context, ArrayList<SongInfo> songs) {
        this.context = context;
        this._songs = songs;
    }

    public interface OnItemClickListener {
        void onItemClick(Button b ,View view, SongInfo obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @Override
    public SongHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View myView = LayoutInflater.from(context).inflate(R.layout.row_song,viewGroup,false);
        return new SongHolder(myView);
    }


    //Bindowanie, łączenie informacji o pliku z konkretnym elementem listy
    @Override
    public void onBindViewHolder(final SongHolder songHolder, final int i) {
        final SongInfo s = _songs.get(i);
        songHolder.tvSongName.setText(_songs.get(i).getSongname());
        songHolder.tvSongArtist.setText(_songs.get(i).getArtistname());
        songHolder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(songHolder.btnAction,v, s, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }


    //Przesłanie danych do RecyclerView
    public class SongHolder extends RecyclerView.ViewHolder {
        TextView tvSongName,tvSongArtist;
        Button btnAction;
        public SongHolder(View itemView) {
            super(itemView);
            tvSongName = (TextView) itemView.findViewById(R.id.tvSongName);
            tvSongArtist = (TextView) itemView.findViewById(R.id.tvArtistName);
            btnAction = (Button) itemView.findViewById(R.id.btnPlay);
        }
    }
}
