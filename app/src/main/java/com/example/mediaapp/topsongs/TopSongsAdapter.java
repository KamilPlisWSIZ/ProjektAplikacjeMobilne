package com.example.mediaapp.topsongs;



        import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

        import com.example.mediaapp.R;
import com.example.mediaapp.api.TrendingAlbum;

        import java.util.List;

        public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongsViewHolders> {

            private List<TrendingAlbum> trending;

            public TopSongsAdapter(List<TrendingAlbum> trending) {
                this.trending = trending;
            }

            @Override
    public TopSongsViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.item_top_songs, parent, false);

                       return new TopSongsViewHolders(view);
           }

            @Override
    public void onBindViewHolder(TopSongsViewHolders holder, int position) {
               TrendingAlbum trendingAlbum = trending.get(position);

               holder.tvPlace.setText(String.valueOf(trendingAlbum.intChartPlace));
                holder.tvAlbum.setText(trendingAlbum.strAlbum);
                holder.tvArtist.setText(trendingAlbum.strArtist);



           }

            @Override
    public int getItemCount() {
               return this.trending != null ? this.trending.size() : 0;
            }

            public class TopSongsViewHolders extends RecyclerView.ViewHolder {

               TextView tvPlace;
                TextView tvAlbum;
        TextView tvArtist;


               public TopSongsViewHolders(View itemView) {
                        super(itemView);

                                tvPlace = itemView.findViewById(R.id.tvPlace);
                   tvAlbum = itemView.findViewById(R.id.tvAlbum);

                        tvArtist = itemView.findViewById(R.id.tvArtist);

                  }
    }

    }