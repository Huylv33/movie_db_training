package com.project.mobile.movie_db_training.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Review;
import com.project.mobile.movie_db_training.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ReviewHolder> {
    private List<Review> mReviews;

    public ReviewsListAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review, parent, false);
        return new ReviewsListAdapter.ReviewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.mAuthor.setText(mReviews.get(position).getAuthor());
        holder.mContent.setText(mReviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mReviews != null ? mReviews.size() : 0;
    }

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.content)
        TextView mContent;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContent.setOnClickListener(view -> {
                setMaxLines(mContent);
            });
        }

        private void setMaxLines(TextView textView) {
            textView.setMaxLines(textView.getMaxLines() == Constants.MAX_LINE_REVIEW ?
                    Integer.MAX_VALUE : Constants.MAX_LINE_REVIEW);
        }
    }
}
