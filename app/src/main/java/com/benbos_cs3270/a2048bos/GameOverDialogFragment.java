package com.benbos_cs3270.a2048bos;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverDialogFragment extends DialogFragment {

    private gameOverReset mCallback;

    public interface gameOverReset{
        void resetAfterLose();
    }

    public GameOverDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.gameOver)
                .setPositiveButton(R.string.resetGame, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.resetAfterLose();
                    }
                });

        return builder.create();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (GameOverDialogFragment.gameOverReset) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement gameOverReset");
        }
    }

}
