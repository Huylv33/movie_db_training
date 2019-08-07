
package com.project.mobile.movie_db_training.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {
    private List<Movie> mMovies;
    private MoviesListContract.View mMoviesListView;
    private Context mContext;

    public MoviesListAdapter(List<Movie> movies, MoviesListContract.View moviesListView, Context context) {
        mMovies = movies;
        mMoviesListView = moviesListView;
        mContext = context;
    }

    @NonNull
    @Override
    public MoviesListAdapter.MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MoviesListAdapter.MoviesListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.MoviesListViewHolder holder, int position) {
        holder.movieTitle.setText(mMovies.get(position).getTitle());
        holder.movieReleaseDate.setText(String.format(mContext.getString(R.string.release_date),
                mMovies.get(position).getReleaseDate()));
        holder.movieRating.setText(String.format(mContext.getString(R.string.rating),
                mMovies.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class MoviesListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_backdrop)
        ImageView backdropImage;
        @BindView(R.id.text_title)
        TextView movieTitle;
        @BindView(R.id.text_rating)
        TextView movieRating;
        @BindView(R.id.text_release_date)
        TextView movieReleaseDate;

        public MoviesListViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}