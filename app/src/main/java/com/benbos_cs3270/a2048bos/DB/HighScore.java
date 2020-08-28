package com.benbos_cs3270.a2048bos.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class HighScore {

    @PrimaryKey(autoGenerate = true)
    private int highScoreID;

    @ColumnInfo(name="high_score")
    private int high_Score;


    @Ignore
    public HighScore(int high_Score) {

        this.high_Score = high_Score;
    }


    public HighScore(int highScoreID, int high_Score) {
        this.highScoreID = highScoreID;
        this.high_Score = high_Score;
    }

    public int getHigh_Score() {
        return high_Score;
    }

    public void setHigh_Score(int high_Score) {
        this.high_Score = high_Score;
    }

    public int getHighScoreID() {
        return highScoreID;
    }

    public void setHighScoreID(int highScoreID) {
        this.highScoreID = highScoreID;
    }
}
