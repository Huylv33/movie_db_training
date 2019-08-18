package com.project.mobile.movie_db_training.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * from movies where favorite = 1")
    List<Movie> getFavorites();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

}
