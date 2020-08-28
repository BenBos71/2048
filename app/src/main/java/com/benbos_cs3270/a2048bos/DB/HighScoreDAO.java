package com.benbos_cs3270.a2048bos.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HighScoreDAO {
    @Query("select * from HighScore order by high_score")
    List<HighScore> getAll();

    @Query("select highScoreID, MAX(high_score) as high_score from HighScore")
    List<HighScore> getHighScore();

    @Update
    void update(HighScore highScore);

    @Insert
    void insert(HighScore highScore);

    @Delete
    void delete(HighScore highScore);
}
