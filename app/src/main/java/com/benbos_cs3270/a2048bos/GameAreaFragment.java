package com.benbos_cs3270.a2048bos;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benbos_cs3270.a2048bos.DB.AppDatabase;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameAreaFragment extends Fragment {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private View root;
    private int pieceArray[][] = new int[4][4];
    private UpdateScore mCallback;
    private int randX, randY, faceValue, tmpX, tmpY, tmp, cX, cY;
    private int startValue = 0;
    private TextView spot00, spot01, spot02, spot03, spot10, spot11, spot12, spot13, spot20, spot21, spot22, spot23, spot30, spot31, spot32 ,spot33;
    private Boolean rowDoneChecker = false;
    private int spotsUsed = 0;
    private Boolean stillMoves = false;

    public interface UpdateScore{
        void scoreUpdater(int addPoints);
        void gameOverCaller();
    }

    public GameAreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                pieceArray[i][j] = 0;
            }
        }
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_game_area, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();



        spot00 = root.findViewById(R.id.spot00);
        spot01 = root.findViewById(R.id.spot01);
        spot02 = root.findViewById(R.id.spot02);
        spot03 = root.findViewById(R.id.spot03);
        spot10 = root.findViewById(R.id.spot10);
        spot11 = root.findViewById(R.id.spot11);
        spot12 = root.findViewById(R.id.spot12);
        spot13 = root.findViewById(R.id.spot13);
        spot20 = root.findViewById(R.id.spot20);
        spot21 = root.findViewById(R.id.spot21);
        spot22 = root.findViewById(R.id.spot22);
        spot23 = root.findViewById(R.id.spot23);
        spot30 = root.findViewById(R.id.spot30);
        spot31 = root.findViewById(R.id.spot31);
        spot32 = root.findViewById(R.id.spot32);
        spot33 = root.findViewById(R.id.spot33);



        /*if (startValue != 2){
            while (startValue < 2) {
                randValAtrandCoord();
            }
        } */
        resetArrayandBoard();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public void randCoordinates(){
        Random randomGenerator = new Random();
        randX = randomGenerator.nextInt(4 - 0) + 0;
        randY = randomGenerator.nextInt(4 - 0) + 0;

        //faceValue = mCallback.setFaceValue(randX, randY);
    }

    public void randValue(){
        Random randomgGenerator = new Random();
        faceValue = randomgGenerator.nextInt(2) + 1;
        if (faceValue == 0 || faceValue == 1){
            faceValue = 2;
        }
        else{
            faceValue = 4;
        }
    }

    public void randValAtrandCoord(){
        Log.d("test", "RandVal");
        Boolean isEmpty = false;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if(pieceArray[i][j] == 0){
                    isEmpty = true;
                    break;
                }
            }
        }
        if(isEmpty == false){
            movesWatcher();
        }
        else {
            Boolean spotFound = false;
            do {
                randCoordinates();
                if (pieceArray[randX][randY] == 0) {
                    startValue += 1;
                    spotFound = true;
                    //pieceArray[randX][randY] = new GamePieceFragment(faceValue);

                    pieceArray[randX][randY] = faceValue;
                    spotsUsed += 1;
                /*if (spotsUsed == 16) {
                    movesWatcher();
                }*/
                    randValue();
                    switch (randX) {
                        case 0:
                            switch (randY) {
                                case 0:
                                    if (spot00 == null) {
                                        spot00 = root.findViewById(R.id.spot00);
                                    }
                                    spot00.setText("" + faceValue);
                                    break;
                                case 1:
                                    if (spot01 == null) {
                                        spot01 = root.findViewById(R.id.spot01);
                                    }
                                    spot01.setText("" + faceValue);
                                    break;
                                case 2:
                                    if (spot02 == null) {
                                        spot02 = root.findViewById(R.id.spot02);
                                    }
                                    spot02.setText("" + faceValue);
                                    break;
                                case 3:
                                    if (spot03 == null) {
                                        spot03 = root.findViewById(R.id.spot03);
                                    }
                                    spot03.setText("" + faceValue);
                                    break;
                            }
                            break;
                        case 1:
                            switch (randY) {
                                case 0:
                                    if (spot10 == null) {
                                        spot10 = root.findViewById(R.id.spot10);
                                    }
                                    spot10.setText("" + faceValue);
                                    break;
                                case 1:
                                    if (spot11 == null) {
                                        spot11 = root.findViewById(R.id.spot11);
                                    }
                                    spot11.setText("" + faceValue);
                                    break;
                                case 2:
                                    if (spot12 == null) {
                                        spot12 = root.findViewById(R.id.spot12);
                                    }
                                    spot12.setText("" + faceValue);
                                    break;
                                case 3:
                                    if (spot13 == null) {
                                        spot13 = root.findViewById(R.id.spot13);
                                    }
                                    spot13.setText("" + faceValue);
                                    break;
                            }
                            break;
                        case 2:
                            switch (randY) {
                                case 0:
                                    if (spot20 == null) {
                                        spot20 = root.findViewById(R.id.spot20);
                                    }
                                    spot20.setText("" + faceValue);
                                    break;
                                case 1:
                                    if (spot21 == null) {
                                        spot21 = root.findViewById(R.id.spot21);
                                    }
                                    spot21.setText("" + faceValue);
                                    break;
                                case 2:
                                    if (spot22 == null) {
                                        spot22 = root.findViewById(R.id.spot22);
                                    }
                                    spot22.setText("" + faceValue);
                                    break;
                                case 3:
                                    if (spot23 == null) {
                                        spot23 = root.findViewById(R.id.spot23);
                                    }
                                    spot23.setText("" + faceValue);
                                    break;
                            }
                            break;
                        case 3:
                            switch (randY) {
                                case 0:
                                    if (spot30 == null) {
                                        spot30 = root.findViewById(R.id.spot30);
                                    }
                                    spot30.setText("" + faceValue);
                                    break;
                                case 1:
                                    if (spot31 == null) {
                                        spot31 = root.findViewById(R.id.spot31);
                                    }
                                    spot31.setText("" + faceValue);
                                    break;
                                case 2:
                                    if (spot32 == null) {
                                        spot32 = root.findViewById(R.id.spot32);
                                    }
                                    spot32.setText("" + faceValue);
                                    break;
                                case 3:
                                    if (spot33 == null) {
                                        spot33 = root.findViewById(R.id.spot33);
                                    }
                                    spot33.setText("" + faceValue);
                                    break;
                            }
                            break;
                    }
                }
            }while(spotFound == false);
        }
    }


    public void movesWatcher(){
        Log.d("test", "MovesWatcher");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(i<3) {
                    if(j<3) {
                        if ((pieceArray[i][j] == pieceArray[i][j + 1]) || (pieceArray[i][j] == pieceArray[i + 1][j])) {
                            stillMoves = true;
                            break;
                        }
                    }else if (pieceArray[i][j] == pieceArray[i + 1][j]){
                        stillMoves = true;
                        break;
                    }
                }else if(j<3){
                    if(pieceArray[i][j] == pieceArray[i][j + 1]){
                        stillMoves = true;
                        break;
                    }
                }
            }
        }
        if(stillMoves == false){
            mCallback.gameOverCaller();
        }

        stillMoves = false;
    }


    public void upDownMove(int startUDIndex){
        rowDoneChecker = false;
        Log.d("test", "upDownMove");
        if (startUDIndex == 1) {
            //swipe up
            tmpX = 0;
            tmpY = 0;
            //tmp = pieceArray[0][0];
            cX = 0;
            cY = 1;

            while(rowDoneChecker == false) {
                if (cX != 4){
                    if (pieceArray[tmpX][tmpY] == 0) {
                        if (pieceArray[cX][tmpY] != 0) {
                            pieceArray[tmpX][tmpY] = pieceArray[cX][tmpY];
                            pieceArray[cX][tmpY] = 0;
                        }
                        cX += 1;
                    } else if (pieceArray[tmpX][tmpY] != 0) {
                        if (pieceArray[cX][tmpY] == 0) {
                            cX += 1;
                        } else if (pieceArray[cX][tmpY] != 0) {
                            if (pieceArray[tmpX][tmpY] == pieceArray[cX][tmpY]) {
                                pieceArray[tmpX][tmpY] = pieceArray[tmpX][tmpY] + pieceArray[cX][tmpY];
                                pieceArray[cX][tmpY] = 0;
                                spotsUsed -= 1;
                                mCallback.scoreUpdater(pieceArray[tmpX][tmpY] + pieceArray[cX][tmpY]);
                            }
                            tmpX += 1;
                            cX = tmpX + 1;
                        }
                    }
                }
                if(tmpX == 4 || cX == 4) {
                    tmpX = 0;
                    cX = 1;
                    tmpY += 1;
                }
                //cY = tmpX + 1;
                if (tmpY == 4){
                    rowDoneChecker = true;
                    //function to set textViews to new values
                    tmp = startValue;
                    //while ( tmp == startValue) {
                        randValAtrandCoord();
                    //}
                    reDrawScreen();
                }
            }
        }else{
            //swipe down
            tmpX = 3;
            tmpY = 3;
            //tmp = pieceArray[3][3];
            cX = 2;
            cY = 3;

            while(rowDoneChecker == false) {
                if (cX != -1){
                    if (pieceArray[tmpX][tmpY] == 0) {
                        if (pieceArray[cX][tmpY] != 0) {
                            pieceArray[tmpX][tmpY] = pieceArray[cX][tmpY];
                            pieceArray[cX][tmpY] = 0;
                        }
                        cX -= 1;
                    } else if (pieceArray[tmpX][tmpY] != 0) {
                        if (pieceArray[cX][tmpY] == 0) {
                            cX -= 1;
                        } else if (pieceArray[cX][tmpY] != 0) {
                            if (pieceArray[tmpX][tmpY] == pieceArray[cX][tmpY]) {
                                pieceArray[tmpX][tmpY] = pieceArray[tmpX][tmpY] + pieceArray[cX][tmpY];
                                pieceArray[cX][tmpY] = 0;
                                spotsUsed -= 1;
                                mCallback.scoreUpdater(pieceArray[tmpX][tmpY] + pieceArray[cX][tmpY]);
                            }
                            tmpX -= 1;
                            cX = tmpX - 1;
                        }
                    }
                }
                if(tmpX == -1 || cX == -1) {
                    tmpX = 3;
                    cX = 2;
                    tmpY -= 1;
                }
                //cY = tmpX + 1;
                if (tmpY == -1){
                    rowDoneChecker = true;
                    //function to set textViews to new values
                    tmp = startValue;
                    //while ( tmp == startValue) {
                        randValAtrandCoord();
                    //}
                    reDrawScreen();
                }
            }
        }
    }



    public void rightLeftMove(int startRLIndex){
        Log.d("test", "rightLeftMove");
        rowDoneChecker = false;
        if (startRLIndex == 1){
            //swipe left
            tmpX = 0;
            tmpY = 0;
            //tmp = pieceArray[0][0];
            cX = 0;
            cY = 1;

            while(rowDoneChecker == false) {
                if (cY != 4){
                    if (pieceArray[tmpX][tmpY] == 0) {
                        if (pieceArray[tmpX][cY] != 0) {
                            pieceArray[tmpX][tmpY] = pieceArray[tmpX][cY];
                            pieceArray[tmpX][cY] = 0;
                        }
                        cY += 1;
                    } else if (pieceArray[tmpX][tmpY] != 0) {
                        if (pieceArray[tmpX][cY] == 0) {
                            cY += 1;
                        } else if (pieceArray[tmpX][cY] != 0) {
                            if (pieceArray[tmpX][tmpY] == pieceArray[tmpX][cY]) {
                                pieceArray[tmpX][tmpY] = pieceArray[tmpX][tmpY] + pieceArray[tmpX][cY];
                                pieceArray[tmpX][cY] = 0;
                                spotsUsed -= 1;
                                mCallback.scoreUpdater(pieceArray[tmpX][tmpY] + pieceArray[tmpX][cY]);
                            }
                            tmpY += 1;
                            cY = tmpY + 1;
                        }
                    }
                }
                if(tmpY == 4 || cY == 4) {
                    tmpY = 0;
                    cY = 1;
                    tmpX += 1;
                }
                //cY = tmpX + 1;
                if (tmpX == 4){
                    rowDoneChecker = true;
                    //function to set textViews to new values
                    tmp = startValue;
                    //while ( tmp == startValue) {
                        randValAtrandCoord();
                    //}
                    reDrawScreen();
                }
            }
        }else{
            //swipe right
            tmpX = 3;
            tmpY = 3;
            //tmp = pieceArray[3][3];
            cX = 3;
            cY = 2;

            while(rowDoneChecker == false) {
                if (cY != -1){
                    if (pieceArray[tmpX][tmpY] == 0) {
                        if (pieceArray[tmpX][cY] != 0) {
                            pieceArray[tmpX][tmpY] = pieceArray[tmpX][cY];
                            pieceArray[tmpX][cY] = 0;
                        }
                        cY -= 1;
                    } else if (pieceArray[tmpX][tmpY] != 0) {
                        if (pieceArray[tmpX][cY] == 0) {
                            cY -= 1;
                        } else if (pieceArray[tmpX][cY] != 0) {
                            if (pieceArray[tmpX][tmpY] == pieceArray[tmpX][cY]) {
                                pieceArray[tmpX][tmpY] = pieceArray[tmpX][tmpY] + pieceArray[tmpX][cY];
                                pieceArray[tmpX][cY] = 0;
                                spotsUsed -= 1;
                                mCallback.scoreUpdater(pieceArray[tmpX][tmpY] + pieceArray[tmpX][cY]);
                            }
                            tmpY -= 1;
                            cY = tmpY - 1;
                        }
                    }
                }
                if(tmpY == -1 || cY == -1) {
                    tmpY = 3;
                    cY = 2;
                    tmpX -= 1;
                }
                //cY = tmpX + 1;
                if (tmpX == -1){
                    rowDoneChecker = true;
                    //function to set textViews to new values
                    tmp = startValue;
                    //while ( tmp == startValue) {
                        randValAtrandCoord();
                    //}
                    reDrawScreen();
                }
            }
        }
    }


    public void reDrawScreen(){
        if (pieceArray[0][0] != 0) {
            spot00.setText(""+pieceArray[0][0]);
        }else{
            spot00.setText("");
        }

        if (pieceArray[0][1] != 0) {
            spot01.setText(""+pieceArray[0][1]);
        }else{
            spot01.setText("");
        }

        if (pieceArray[0][2] != 0) {
            spot02.setText(""+pieceArray[0][2]);
        }else{
            spot02.setText("");
        }

        if (pieceArray[0][3] != 0) {
            spot03.setText(""+pieceArray[0][3]);
        }else{
            spot03.setText("");
        }

        if (pieceArray[1][0] != 0) {
            spot10.setText(""+pieceArray[1][0]);
        }else{
            spot10.setText("");
        }

        if (pieceArray[1][1] != 0) {
            spot11.setText(""+pieceArray[1][1]);
        }else{
            spot11.setText("");
        }

        if (pieceArray[1][2] != 0) {
            spot12.setText(""+pieceArray[1][2]);
        }else{
            spot12.setText("");
        }

        if (pieceArray[1][3] != 0) {
            spot13.setText(""+pieceArray[1][3]);
        }else{
            spot13.setText("");
        }

        if (pieceArray[2][0] != 0) {
            spot20.setText(""+pieceArray[2][0]);
        }else{
            spot20.setText("");
        }

        if (pieceArray[2][1] != 0) {
            spot21.setText(""+pieceArray[2][1]);
        }else{
            spot21.setText("");
        }

        if (pieceArray[2][2] != 0) {
            spot22.setText(""+pieceArray[2][2]);
        }else{
            spot22.setText("");
        }

        if (pieceArray[2][3] != 0) {
            spot23.setText(""+pieceArray[2][3]);
        }else{
            spot23.setText("");
        }

        if (pieceArray[3][0] != 0) {
            spot30.setText(""+pieceArray[3][0]);
        }else{
            spot30.setText("");
        }

        if (pieceArray[3][1] != 0) {
            spot31.setText(""+pieceArray[3][1]);
        }else{
            spot31.setText("");
        }

        if (pieceArray[3][2] != 0) {
            spot32.setText(""+pieceArray[3][2]);
        }else{
            spot32.setText("");
        }

        if (pieceArray[3][3] != 0) {
            spot33.setText(""+pieceArray[3][3]);
        }else{
            spot33.setText("");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (GameAreaFragment.UpdateScore) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement UpdateScore");
        }
    }

    public void resetArrayandBoard(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                pieceArray[i][j] = 0;
            }
        }
        spotsUsed = 0;

        if (spot00 == null){
            spot00 = root.findViewById(R.id.spot00);
        }
        spot00.setText("");

        if (spot01 == null){
            spot01 = root.findViewById(R.id.spot01);
        }
        spot01.setText("");

        if (spot02 == null){
            spot02 = root.findViewById(R.id.spot02);
        }
        spot02.setText("");

        if (spot03 == null){
            spot03 = root.findViewById(R.id.spot03);
        }
        spot03.setText("");

        if (spot10 == null){
            spot10 = root.findViewById(R.id.spot10);
        }
        spot10.setText("");

        if (spot11 == null){
            spot11 = root.findViewById(R.id.spot11);
        }
        spot11.setText("");

        if (spot12 == null){
            spot12 = root.findViewById(R.id.spot12);
        }
        spot12.setText("");

        if (spot13 == null){
            spot13 = root.findViewById(R.id.spot13);
        }
        spot13.setText("");

        if (spot20 == null){
            spot20 = root.findViewById(R.id.spot20);
        }
        spot20.setText("");

        if (spot21 == null){
            spot21 = root.findViewById(R.id.spot21);
        }
        spot21.setText("");

        if (spot22 == null){
            spot22 = root.findViewById(R.id.spot22);
        }
        spot22.setText("");

        if (spot23 == null){
            spot23 = root.findViewById(R.id.spot23);
        }
        spot23.setText("");

        if (spot30 == null){
            spot30 = root.findViewById(R.id.spot30);
        }
        spot30.setText("");

        if (spot31 == null){
            spot31 = root.findViewById(R.id.spot31);
        }
        spot31.setText("");

        if (spot32 == null){
            spot32 = root.findViewById(R.id.spot32);
        }
        spot32.setText("");

        if (spot33 == null){
            spot33 = root.findViewById(R.id.spot33);
        }
        spot33.setText("");


        startValue = 0;
        while (startValue < 2) {
            randValAtrandCoord();
        }

    }

}
