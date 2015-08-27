package com.example.hover.onebeen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.diarylist.TravelDiaryListFragment;

import com.example.hover.onebeen.diary.MakeDiary;

public class HomeFragment extends Fragment{

    private static int TRAVEL_START_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.home_activity, null);

        final FragmentManager fragmentManager = getFragmentManager();

        root.findViewById(R.id.travel_start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeDiary.class);
                startActivityForResult(intent, TRAVEL_START_CODE);
            }
        });

        root.findViewById(R.id.traven_ongoing_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelDiaryListFragment travelDiaryListFragment = new TravelDiaryListFragment();

                Bundle args = new Bundle();
                args.putString("travelStatus", TravelStatus.ONGOING.getValue());

                travelDiaryListFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, travelDiaryListFragment)
                        .commit();
            }
        });

        root.findViewById(R.id.travel_been_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelDiaryListFragment travelDiaryListFragment = new TravelDiaryListFragment();

                Bundle args = new Bundle();
                args.putString("travelStatus", TravelStatus.BEEN.getValue());

                travelDiaryListFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, travelDiaryListFragment)
                        .commit();
            }
        });

        root.findViewById(R.id.travel_planning_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelDiaryListFragment travelDiaryListFragment = new TravelDiaryListFragment();

                Bundle args = new Bundle();
                args.putString("travelStatus", TravelStatus.PLANNING.getValue());

                travelDiaryListFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, travelDiaryListFragment)
                        .commit();
            }
        });

        return root;
    }
}
