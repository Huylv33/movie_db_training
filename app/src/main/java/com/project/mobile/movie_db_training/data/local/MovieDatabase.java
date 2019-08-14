package com.project.mobile.movie_db_training.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.mobile.movie_db_training.data.model.Movie;
import static  com.project.mobile.movie_db_training.data.local.MovieDatabase.DATABASE_VERSION;
@Database(entities = {Movie.class}, version = DATABASE_VERSION, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase sMovieDatabase;
    public static final int DATABASE_VERSION = 2;
    private static final Object LOCK = new Object();
    public abstract MovieDAO movieDAO();
    public static final String DATABASE_NAME = "Room-database";
    public static MovieDatabase getInstance(final Context context) {
        if (sMovieDatabase == null) {
            synchronized (LOCK) {
                sMovieDatabase = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sMovieDatabase;
    }
}

