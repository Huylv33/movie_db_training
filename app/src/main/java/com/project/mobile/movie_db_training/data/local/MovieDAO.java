package com.project.mobile.movie_db_training.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movies WHERE mFavorite = 1")
    List<Movie> loadFavorites();

    @Query("SELECT * FROM movies WHERE mWatchList = 1")
    List<Movie> loadWatchList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie);

    @Update
    int updateMovie(Movie... movies);

    @Delete
    int deleteMovie(Movie... movies);
}
