
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
import com.project.mobile.movie_db_training.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;

    public MoviesListAdapter(List<Movie> movies, Context context) {
        mMovies = movies;
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
        holder.mMovieTitle.setText(mMovies.get(position).getTitle());
        holder.mMovieReleaseDate.setText(String.format(mContext.getString(R.string.release_date),
                mMovies.get(position).getReleaseDate()));
        holder.mMovieRating.setText(String.format(mContext.getString(R.string.rating),
                mMovies.get(position).getVoteAverage()));
        Picasso.get().load(Constants.IMAGE_BASE_URL + mMovies.get(position)
                .getBackdropPath()).into(holder.mBackdropImage);
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class MoviesListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_backdrop)
        ImageView mBackdropImage;
        @BindView(R.id.text_title)
        TextView mMovieTitle;
        @BindView(R.id.text_rating)
        TextView mMovieRating;
        @BindView(R.id.text_release_date)
        TextView mMovieReleaseDate;

        public MoviesListViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}