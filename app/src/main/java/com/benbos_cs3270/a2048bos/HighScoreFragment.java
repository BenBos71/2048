package com.benbos_cs3270.a2048bos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benbos_cs3270.a2048bos.DB.AppDatabase;
import com.benbos_cs3270.a2048bos.DB.HighScore;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighScoreFragment extends Fragment {

    private View root;
    private TextView CurrentScore, HighScore;
    private int CurrentPoints = 0;
    private int HighPoints;
    private String CurrentPointsText;
    private Boolean newHigh = false;

    public HighScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_high_score, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        /*First entry in database
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getContext())
                        .highScoreDAO()
                        .insert(new HighScore(0));
            }
        }).start();*/


        if(CurrentScore == null){
            CurrentScore = root.findViewById(R.id.tvCurrentScore);
        }
        //CurrentScore.findViewById(R.id.tvCurrentScore);
        if(HighScore == null){
            HighScore = root.findViewById(R.id.tvHighScore);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<HighScore> highListScore = AppDatabase.getInstance(getContext())
                        .highScoreDAO()
                        .getHighScore();

                HighScore test = highListScore.get(0);
                HighPoints = test.getHigh_Score();
                Log.d("test", "HighPoints" + HighPoints);
                HighScore.setText(""+HighPoints);
            }
        }).start();



        //HighScore.findViewById(R.id.tvHighScore);
    }

    public void updateCurrentScore(int addPoints){
        CurrentPoints = CurrentPoints + addPoints;
        CurrentPointsText = Integer.toString(CurrentPoints);
        CurrentScore.setText(CurrentPointsText);



        if (CurrentPoints >= HighPoints){
            Log.d("test", "about to set high score variable");
            HighPoints = Integer.parseInt(HighScore.getText().toString());
            HighPoints = CurrentPoints;
            setHighScore();
            newHigh = true;
        }
    }

    public void resetCurrentScore(){
        CurrentPoints = 0;
        CurrentScore.setText("");
        if (newHigh == true){
            insertNewHighScore();
            newHigh = false;
        }
    }

    public void setHighScore(){
        //this.HighPoints = highScore;
        //HighPoints = Integer.parseInt(HighScore.getText().toString());
        Log.d("test", "Setting high score variable");
        HighScore.setText(""+HighPoints);
    }

    public void insertNewHighScore(){
        //HighPoints = Integer.parseInt(HighScore.getText().toString());
        Log.d("test", "inserting new high score: " + HighPoints);
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getContext())
                        .highScoreDAO()
                        .insert(new HighScore(HighPoints));
            }
        }).start();

    }
}
