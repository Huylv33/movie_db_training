package com.project.mobile.movie_db_training.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.mobile.movie_db_training.data.model.FavoriteEntity;

import java.util.List;

@Dao
public interface FavoritesDao {
    @Query("SELECT * from favorites")
    List<FavoriteEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoriteEntity favoriteEntity);

    @Query("Select * from favorites where id =:id")
    FavoriteEntity getById(String id);

    @Query("DELETE FROM favorites WHERE id =:id")
    int deleteById(String id);
}
