package com.benbos_cs3270.a2048bos;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.benbos_cs3270.a2048bos.DB.AppDatabase;

import static com.benbos_cs3270.a2048bos.GameAreaFragment.SWIPE_THRESHOLD;
import static com.benbos_cs3270.a2048bos.GameAreaFragment.SWIPE_VELOCITY_THRESHOLD;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GameAreaFragment.UpdateScore, ResetFragment.buttonListener, GameOverDialogFragment.gameOverReset {

    private GestureDetector gestureDetector;
    private FragmentManager fm;
    private int retVal;
    private int highScore;
    private GameAreaFragment GAF;
    private HighScoreFragment HSF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this);

    }

    @Override
    protected void onStart() {
        super.onStart();



    }




    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;

        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) > Math.abs(diffY)){
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                if (diffX > 0){
                    onSwipeRight();
                }else{
                    onSwipeLeft();
                }
                result = true;
            }
        }else{
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){
                if (diffY > 0){
                    onSwipeBottom();
                }else{
                    onSwipeTop();
                }
                result = true;
            }
        }

        return result;
    }

    private void onSwipeTop(){
        Log.d("test", "SWIPE UP");
        if (GAF == null){
            FragmentManager fm = getSupportFragmentManager();
            GAF = (GameAreaFragment) fm.findFragmentById(R.id.fragmentGameArea);
        }
        GAF.upDownMove(1);
    }

    private void onSwipeBottom(){
        Log.d("test", "SWIPE DOWN");
        if (GAF == null){
            FragmentManager fm = getSupportFragmentManager();
            GAF = (GameAreaFragment) fm.findFragmentById(R.id.fragmentGameArea);
        }
        GAF.upDownMove(0);
    }

    private void onSwipeLeft() {
        Log.d("test", "SWIPE LEFT");
        if (GAF == null){
            FragmentManager fm = getSupportFragmentManager();
            GAF = (GameAreaFragment) fm.findFragmentById(R.id.fragmentGameArea);
        }
        GAF.rightLeftMove(1);
    }

    private void onSwipeRight() {
        Log.d("test", "SWIPE RIGHT");
        if (GAF == null){
            FragmentManager fm = getSupportFragmentManager();
            GAF = (GameAreaFragment) fm.findFragmentById(R.id.fragmentGameArea);
        }
        GAF.rightLeftMove(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void scoreUpdater(int addPoints) {
        if (HSF == null){
            FragmentManager fm = getSupportFragmentManager();
            HSF = (HighScoreFragment) fm.findFragmentById(R.id.fragmentHighScore);
        }
        HSF.updateCurrentScore(addPoints);

    }

    @Override
    public void gameOverCaller() {
        GameOverDialogFragment dialog = new GameOverDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "Game Over");
    }

    @Override
    public void resetGame() {
        resetThisGame();
    }


    @Override
    public void resetAfterLose() {
        resetThisGame();
    }


    public void resetThisGame(){
        if (GAF == null){
            FragmentManager fm = getSupportFragmentManager();
            GAF = (GameAreaFragment) fm.findFragmentById(R.id.fragmentGameArea);
        }
        GAF.resetArrayandBoard();
        if (HSF == null){
            FragmentManager fm = getSupportFragmentManager();
            HSF = (HighScoreFragment) fm.findFragmentById(R.id.fragmentHighScore);
        }
        HSF.resetCurrentScore();
    }

}
