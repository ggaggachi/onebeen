package com.example.hover.onebeen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hover.onebeen.puzzle.PuzzleDummyActivity;

/**
 * Created by hover on 2015. 8. 25..
 */
public class NavigationFragment extends Fragment {

//    private Button button1;
//    private Button button2;
//    private Button button3;
//    private Button button4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foutbtn_activity, null);
//        initialize(view);
        return view;
    }

    public void onClick() {
        ((PuzzleDummyActivity) getActivity()).closeDrawers();
    }

//    private void initialize(View parentView) {
//        button1 = (Button) parentView.findViewById(R.id.button1);
//        button1 = (Button) parentView.findViewById(R.id.button1);
//        button1 = (Button) parentView.findViewById(R.id.button1);
//        button1 = (Button) parentView.findViewById(R.id.button1);
//
//    }


}
