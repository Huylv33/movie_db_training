package com.project.mobile.movie_db_training.genre;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Genre;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenresListAdapter extends
        RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder> {
    private List<Genre> mGenreList;
    private GenresListContract.View mGenresListView;

    public GenresListAdapter(List<Genre> mGenreList, GenresListContract.View mGenresListView) {
        this.mGenreList = mGenreList;
        this.mGenresListView = mGenresListView;
    }

    @NonNull
    @Override
    public GenresListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item, parent, false);
        return new GenresListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresListViewHolder holder, int position) {
        holder.mGenreText.setText(mGenreList.get(position).getName());
        holder.itemView.setOnClickListener(view -> {
            mGenresListView.onGenreClick(mGenreList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mGenreList != null ? mGenreList.size() : 0;
    }

    static class GenresListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_genre_detail)
        CardView mGenreCard;
        @BindView(R.id.text_genre_name)
        TextView mGenreText;

        GenresListViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
