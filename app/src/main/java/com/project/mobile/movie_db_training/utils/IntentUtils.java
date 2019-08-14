package com.project.mobile.movie_db_training.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.project.mobile.movie_db_training.list.MoviesListActivity;

public class IntentUtils {
    public static void startMoviesListActivity(Context context, String data) {
        Intent intent = new Intent(context, MoviesListActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.LIST_TYPE, data);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

}
