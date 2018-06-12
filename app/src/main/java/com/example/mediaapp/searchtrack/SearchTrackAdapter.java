package com.example.mediaapp.searchtrack;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mediaapp.R;
import com.example.mediaapp.api.SearchTrack;

import java.util.List;

public class SearchTrackAdapter extends RecyclerView.Adapter<SearchTrackAdapter.SearchTrackViewHolder> {

    private List<SearchTrack> tracks;

    public SearchTrackAdapter(List<SearchTrack> tracks) {
        this.tracks = tracks;
    }

    @Override
    public SearchTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_search_track, parent, false);

        return new SearchTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchTrackViewHolder holder, int position) {
        SearchTrack track = tracks.get(position);

        holder.tvTrack.setText(track.strTrack);
        holder.tvTrackAlbum.setText(track.strAlbum);
        holder.tvTrackArtist.setText(track.strArtist);
        holder.tvGenre.setText(track.strGenre);
    }

    @Override
    public int getItemCount() {
        return tracks == null ? 0 : tracks.size();
    }

    public class SearchTrackViewHolder extends RecyclerView.ViewHolder {

        TextView tvTrack;
        TextView tvTrackAlbum;
        TextView tvTrackArtist;
        TextView tvGenre;

        public SearchTrackViewHolder(View itemView) {
            super(itemView);

            tvTrack = itemView.findViewById(R.id.tvTrack);
            tvTrackAlbum = itemView.findViewById(R.id.tvTrackAlbum);
            tvTrackArtist = itemView.findViewById(R.id.tvTrackArtist);
            tvGenre = itemView.findViewById(R.id.tvGenre);

        }
    }

}