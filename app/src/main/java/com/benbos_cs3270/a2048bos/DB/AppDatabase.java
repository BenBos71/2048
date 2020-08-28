package com.benbos_cs3270.a2048bos.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HighScore.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance != null){
            return instance;
        }

        instance = Room.databaseBuilder(context, AppDatabase.class, "high_score-database").build();

        return instance;
    }

    public abstract HighScoreDAO highScoreDAO();

}
