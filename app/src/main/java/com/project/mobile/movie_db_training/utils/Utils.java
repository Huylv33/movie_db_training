package com.project.mobile.movie_db_training.utils;

import android.content.Context;
import android.content.Intent;

import androidx.cardview.widget.CardView;

import com.project.mobile.movie_db_training.list.MoviesListActivity;

public class Utils {
    public static String getTitleFromListType(String listType) {
        switch (listType) {
            case Constants.NOW_PLAYING:
                return Constants.NOW_PLAYING_TITLE_BAR;
            case Constants.POPULAR:
                return Constants.POPULAR_TITLE_BAR;
            case Constants.UPCOMING:
                return Constants.UPCOMING_TITLE_BAR;
            case Constants.TOP_RATED:
                return Constants.TOP_RATED_TITLE_BAR;
            default:
                break;
        }
        return null;
    }

    public static void setListenerForMovieCard(CardView cardView, Context context, String data) {
        cardView.setOnClickListener(view -> {
                    Intent intent = new Intent(context, MoviesListActivity.class);
                    intent.putExtra(Constants.LIST_TYPE, data);
                    context.startActivity(intent);
                }
        );
    }
}
