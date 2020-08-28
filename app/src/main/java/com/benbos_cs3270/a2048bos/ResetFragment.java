package com.benbos_cs3270.a2048bos;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetFragment extends Fragment {

    private View root;
    private buttonListener mCallback;

    public interface buttonListener{
        void resetGame();
    }


    public ResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_reset, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button ResetButton = root.findViewById(R.id.btnReset);
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.resetGame();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (buttonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement buttonListener");
        }
    }
}
