package com.project.mobile.movie_db_training.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.mobile.movie_db_training.data.model.WatchlistEntity;

import java.util.List;

@Dao
public interface WatchlistDao {
    @Query("SELECT * FROM watchlist")
    List<WatchlistEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(WatchlistEntity watchlistEntity);

    @Query("Select * from watchlist where id =:id")
    WatchlistEntity getById(String id);

    @Query("DELETE FROM watchlist WHERE id =:id")
    int deleteById(String id);
}
